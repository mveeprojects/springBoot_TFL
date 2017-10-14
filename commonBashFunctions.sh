#!/usr/bin/env bash

function stopAppIfRunning (){
    dockerProcesses=`docker ps`
    containerID=$(echo "$dockerProcesses" | grep "tflapp" | awk '{print $1}')
    if [ -z ${containerID} ] ; then
        echo ""
        echo "*************************************************************"
        echo "App not currently running"
        echo "*************************************************************"
        echo ""
    else
        echo ""
        echo "*************************************************************"
        echo "App is currently running, stopping and removing container now"
        echo "*************************************************************"
        echo "Container ID is: $containerID"

        echo "Stopping old container"
        docker stop ${containerID}

        echo "Removing old container"
        docker rm ${containerID}
        echo ""
    fi
}