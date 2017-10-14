#!/usr/bin/env bash

mvn clean install
docker build -t springboot_tfl .
docker images