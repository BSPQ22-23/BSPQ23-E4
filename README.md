BSPQ23-E4
===================
## IDEA
     The idea of our project is to create an app to buy computer products. We are focusing on three specific ones, which are computers, consoles and tablets. There             will be two types of users, the client, which will be able to register on their own and login to buy the available products, and the admin, which can only be               registered by another admin, and will be able to add new products and manage the app and their clients.


This example relies on the DataNucleus Maven plugin. Check the database configuration in the *datanucleus.properties* file and the JDBC driver dependency specified in the *pom.xml* file.

Run the following command to build everything and enhance the DB classes:

      mvn clean compile

Make sure that the database was correctly configured. Use the contents of the file *create-productsdb.sql* to create the database and grant privileges(Only if you do not have the database created do this step). For example,

      mysql –uroot -p < sql/create-productsdb.sql

Run the following command to create database schema for this sample.

      mvn datanucleus:schema-create
Run the following command to Open the server

     mvn jetty:run
Open antoher console and put the following to launch the main example class
      
      mvn clean compile
      mvn exec:java -Pclient

Run the following command to remove the database schema
   
      mvn datanucleus:schema-delete

Run the following command to generate the Javadoc documentation inside the *target/site/apidocs* directory

      mvn javadoc:javadoc

Before running the performance tests, type this only once in your cmd : 

      set JDK_JAVA_OPTIONS=--add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.time=ALL-UNNAMED

Run the tests : 

      mvn test

Generate documentation : 

      mvn doxygen:report

Move the html documentation to docs directory :

      mvn validate


## ✒️ Authors
Mikel
Ana   
Markel
Javi
Arkaitz
