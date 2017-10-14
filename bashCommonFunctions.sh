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