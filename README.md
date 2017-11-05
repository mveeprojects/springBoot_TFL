# springBoot_TFL


This project is aimed at pulling data from the Transport For London API, then manipulating it and outputting it according to requests

So far it retrieves data on the status of all lines from the /status API linked below, maps this to a simple java object of my own then passes this to thymeleaf temlplates accessible via localhost:8080/. 

To run it as an ordinary Spring Boot app run the main method in the Application.java class and navigate to _http://localhost:8080/_ in your browser.

Uses a local Mongo DB with default port (27017) so ensure Mongo is running locally before starting application.

New endpoint added to view the history of a given line based on local mongo data __localhost:8080/linehistory?linename=Metropolitan__


**TFL APIs**

https://api-portal.tfl.gov.uk/docs

https://api.tfl.gov.uk/line/mode/tube/status


### Docker

**Note: Deploying this application via Docker is considered 'prod mode' therefore the port used to access it via HTTP is 80 rather than 8080 in 'dev mode' above (considered dev mode if running in the IDE via run config etc.).**

Currently figuring out a bug with docker and using local Mongo persistence so as a temporary workaround, in order to deploy via docker complete the following steps first:
* Open Services/TFLStatusService.java
* Comment out the call to __saveToMongo();__ in the __runAllStatusChecks()__ method
* Run as normal according to below instructions

To build and run a docker image of this project I've set up a Dockerfile and accompanying shell script in the project root directory.

Running '__./init.sh__' from the command line does the following:
* Downloads Alpine OS
* Builds a new image of the application
* Stops and removes any other locally running containers of this application if there are any
* Runs a new container based on the new image on port 80


### Gatling Performance Tests

To run the gatling performance tests use the following command at the root of the project:

__mvn clean gatling:execute__

The pom.xml has been edited so that the  gatling tests are not ran on deployment (see Docker section above), however this can be enabled by uncommenting the executions section of the gatling-maven-plugin.
