The CRUD-Gradebook application is a distributed application that lets an instructor store and retreive grades of a class in a gradebook.
The application consist of two seperate client and server projects.

The server application uses Jersey 2.0 server and uses default object mapper MOXy to marshel and unmarshel object data.

Application Build Requirement

Java 7
Maven
An active internet connection.

To build install server:

cd GradeBookAppSrv/
mvn install

To build install server:

cd GradeBookAppCli/
mvn install


Server Application Deployment Requirement
Apache Tomcat 9.0 or Glassfish 4.0

********************************************************************************
	IMPORTANT : NOTICE OF GLASSFISH BUG ON VERSION 4.1.x
********************************************************************************

The application is tested both on Glasfish 4.0 and Apache Tomcat. The application DOES NOT work on Glassfish 4.1.x because of the following bug:

http://stackoverflow.com/questions/33319659/moxy-exceptions-in-javaee-jersey-2-0-project

This bug is well documented all over the internet and is not the problem with the application itself. Please take the above fact into consideration before running the application.

As of now , the client application relative URL is configured to work with Glassfish 4.0. The client assumes that server application is hosted at :
http://localhost:8080/GradeBookAppSrv/api/

If you are running the application on Apache Tomcat, the BASE_URL has to be modified accordingly in the client.
By default, the BASE_URI for tomcat will be :
http://localhost:8080/api/
If you want to use tomcat to deploy, you will have to modify BaseCl.java files to edit
BASE_URI variable to:
http://localhost:8080/api/

********************************************************************************



