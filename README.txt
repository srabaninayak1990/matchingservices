**********************************
*  Walmart Coding Assignment
*  Matching Service
*
*  Srabani Nayak 07/14/2019
**********************************

Matching service allows user to reserve a technician based on specific customer issues.
This service will identify all the technicians who has capability to serve the user requests and check the availability of them and 
if any of them are available then assign the request to first available technicians. 
Once the technician is identified and assigned then generate a confirmation number to customer.

This service is designed in simple REST service using Springboot framework and in memory database H2

===================================
1. Build Project By Maven
===================================

Run below command inside of this project folder, matchingservices-0.0.1.jar will be created under ./target inside of project directory

$ mvn clean install

===================================
2. Run As Packaged Application
===================================

For running this service with default configuration, just run below command in the folder which contains matchingservices-0.0.1.jar
Default port will be 9797.


$ java -jar ./target/matchingservices-0.0.1.jar 