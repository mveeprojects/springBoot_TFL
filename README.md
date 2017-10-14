# springBoot_TFL


This project is aimed at pulling data from the Transport For London API, then manipulating it and outputting it according to requests

So far it retrieves data on the status of all lines from the /status API linked below, maps this to a simple java object of my own and outputs to localhost:8080/ as a list of results in simple JSON. 

_localhost:8080/_ - view status of all lines

_localhost:8080/issues_ - view lines with service issues

_localhost:8080/checkline?line=**line_name**_ - check the status of a specific line

**TFL APIs**

https://api-portal.tfl.gov.uk/docs

**Example call to TFL status endpoint**

https://api.tfl.gov.uk/line/mode/tube/status


### Docker

To build and run a docker image of this project I've set up a Dockerfile and accompanying shell script in the project root directory.

Running '__sh buildandrundocker.sh__' from the command line does the following:
* downloads Alpine OS
* builds a new image of the application
* stops and removes any other locally running containers of this application if there are any
* runs a new container based on the new image on port 80
