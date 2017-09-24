# springBoot_TFL

This project is aimed at pulling data from the Transport For London API, then manipulating it and outputting it according to requests

So far it retrieves data on the status of all lines from the /status API linked below, maps this to a simple java object of my own and outputs to localhost:8080/ as a list of results in simple JSON. 

_localhost:8080/_ - view status of all lines

_localhost:8080/issues_ - view lines with service issues

_localhost:8080/checkline?line=<linename>_ - check the status of a specific line

## TFL APIs ##

https://api-portal.tfl.gov.uk/docs

## Example call ##

https://api.tfl.gov.uk/line/mode/tube/status
