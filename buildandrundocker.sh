#!/usr/bin/env bash

source bashCommonFunctions.sh

echo "Pulling down image of Alpine OS"
docker pull openjdk:8-jdk-alpine

echo "Compiling application and building Docker image"
mvn clean install docker:build

images=`docker images`
IMAGEID=$(echo "$images" | grep springboottfl | awk '{print $3}')
echo "Docker image created with IMAGE ID: $IMAGEID"

stopAppIfRunning

echo "app is now being started up"
docker run -d -p80:8080 --name tflapp ${IMAGEID}