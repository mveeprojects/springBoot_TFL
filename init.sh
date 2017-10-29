#!/usr/bin/env bash

source commonBashFunctions.sh

echo ""
echo "***********************************************************"
echo "Pulling down image of Alpine OS"
echo "***********************************************************"
docker pull openjdk:8-jdk-alpine
echo ""

echo ""
echo "*************************************************************"
echo "Compiling application and building Docker image"
echo "*************************************************************"
mvn clean install docker:build
echo ""

images=`docker images`
IMAGEID=$(echo "$images" | grep springboottfl | awk '{print $3}')
echo ""
echo "*************************************************************"
echo "Docker image created with IMAGE ID: $IMAGEID"
echo "*************************************************************"
echo ""

stopAppIfRunning

echo ""
echo "*************************************************************"
echo "Starting app in new container"
echo "*************************************************************"
#Ask Preeyan for his wisdom on getting containerised app to use local mongo (non-dockerised)
#docker run -d -p80:8080 -p49155:27017 --name tflapp --network=host ${IMAGEID}
docker run -d -p80:8080 --name tflapp ${IMAGEID}
echo ""


echo ""
echo "*************************************************************"
echo "Removing old images (ignore error responses from daemon)"
echo "*************************************************************"
docker rmi $(docker images -q)
echo ""