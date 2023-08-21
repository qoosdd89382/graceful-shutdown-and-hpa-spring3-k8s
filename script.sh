#!/bin/bash

dockerhub_repo=$1
project_name=$2

echo "Processing folder: $pwd"

chmod +x docker-entrypoint.sh

# maven build
docker run -it --rm --name my-maven-project -v ~/.m2:/root/.m2 -v $(pwd):/app maven:3.8.3-amazoncorretto-17 mvn clean install -f app/pom.xml -Dmaven.test.skip=true

# docker build
docker build . -t $dockerhub_repo/$project_name
docker push $dockerhub_repo/$project_name
