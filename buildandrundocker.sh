#!/usr/bin/env bash

function stopAppIfRunning (){
    dockerProcesses=`docker ps`
    containerID=$(echo "$dockerProcesses" | grep "tflapp" | awk '{print $1}')
    if [ -z ${containerID} ] ; then
        echo "app not currently running"
    else
        echo "app is currently running will stop and remove container now"
        echo "container ID is: $containerID"

        echo "stopping old container"
        docker stop ${containerID}

        echo "removing old container"
        docker rm ${containerID}
    fi
}

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