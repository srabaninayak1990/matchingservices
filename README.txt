**********************************
*  Walmart Coding Assignment
*  Matching Service
*
*  Srabani Nayak 07/14/2019
**********************************

Matching service allows user to reserve a technician based on specific customer issues.
This service will identify all the technicians who has capability to serve the user issues and check the availability of them and if any of them are available then assign the request to first available technicians. 
Once the technician is identified and assigned then generate a confirmation reservation number to customer.

Once the customer received a confirmation number , I have assumed that Technician will visit the customer location and serve the request. 
Upon completion of the customer request , technician can update the work completion status & date using the "updateWorkStatus" service.

This application is implemented using Springboot framework for REST services and H2 in memory database for data storage.

===================================
Build Project By Maven
===================================

Run below command inside of this project folder, matchingservices-0.0.1-SNAPSHOT.jar will be created under ./target inside of project directory

$ mvn clean install

===================================
Run As Packaged Application
===================================

For running this service with default configuration, just run below command in the folder which contains matchingservices-0.0.1-SNAPSHOT.jar
Default port will be 9797.


$ java -jar ./target/matchingservices-0.0.1-SNAPSHOT.jar 

========================================
H2 Databse Console to view the DB tables
========================================

We can view the database tables and go through the data using H2 database console available on port 9797

http://localhost:9797/matching-service

NOTE: Make sure JDBC URL, user name and password should be entered as per the configuration set in application.properties file.

Driver Class = org.h2.Driver
JDBC URL = jdbc:h2:mem:matching-service
User Name = srabani
Password = srabani

========================================
Swagger Documentation for REST services
========================================

We can view the documentation for various implemented REST services using below swagger link

http://localhost:9797/swagger-ui.html


