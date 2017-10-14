#!/usr/bin/env bash

mvn clean install docker:build
docker images

#docker run -d -p888:8080 --name tflapp 2c77993eb237
