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

To build a docker image of this project locally I've set up a Dockerfile and accompanying shell script in the project root directory.

Therefore to create your own locally runnable docker image just run '__sh dockerbuild.sh__' from the command line, this will also run the 'docker images' command so you can grab the IMAGE ID needed to run the image (next step)

Then to run the container on a localhost port (e.g. port 666) run '__docker run -d -p666:8080 IMAGE_ID__' (this will map localhost:666 to access the containers port 8080). Now open http://localhost:666 in your browser to see it in all its majesty.


