
# Graceful Shutdown
## Ref

- [如何优雅地停止 Spring Boot 应用？ - 知乎 (zhihu.com)](https://zhuanlan.zhihu.com/p/143469078)
- [qoosdd89382/SpringBoot-Learn forked from wupeixuan/SpringBoot-Learn](https://github.com/qoosdd89382/SpringBoot-Learn/tree/master/graceful-shutdown)

---

# HPA
## Build

```bash
sh script.sh {image_repo} {image_name}
```

For example: 
```bash
sh script.sh qoosdd89382 graceful
```

## Deploy

Edit `/deploy/deployment.yaml` by changing `image: ` field to your image repository, then run:

```bash
kubectl apply -f deploy/deployment.yaml
kubectl apply -f deploy/hpa.yaml
kubectl apply -f deploy/service.yaml
```

### Deployment Availability Test

```bash
kubectl port-forward svc/graceful 8089:8089
```

Access `localhost:8089/hpa` on your host, you will get a message like `success`.

## Test For HPA

```bash
kubectl run curl --image=alpine/curl --rm -it -- sh 
```
Then run command below in the pod:
```bash
while true; do curl graceful:8089/hpa; done
```

### Watch

Highly recommend to install `watch` tool. In Mac OSX:
```bash
brew install watch
```

After installing, run:

```bash
watch kubectl top pod
```


## Notice

You need to check the version of HPA from your Kubernetes cluster:
```bash
$ kubectl api-versions | grep autoscaling                                                                                                                                                            ─╯

autoscaling/v1
autoscaling/v2
autoscaling/v2beta2
```

HorizontalPodAutoscaler in version "v1" cannot be handled as a HorizontalPodAutoscaler: 
```plantext
strict decoding error: unknown field "spec.behavior", unknown field "spec.metrics".
```