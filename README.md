# Requirement
- JDK 1.8
- Maven >=3.3.3
- Servlet > 3.X
- target runtime : TOMCAT, TOM-EE

# OpenSource Framework
- Spring > 4
- Spring-boot
- Spring-ws
- Spring-jdbc
- Spring-jms
- Spring-restful
- Annotation Spring config only.

# Architecture 
- Microservices for each main function has their own war file.
- restfulWEB to handle all the restful request and fire JMS message to broker.
- jmcClient to manage the request from JMS broker and calculate the gcd value and store into database.
- soapWEB to handle all the soap request

# Software dependence
- EAR -> restfulWEB, soapWEB, jmsClient
- restfulWEB -> unico-common
- soapWEB -> unico-common
- jmsClient -> unico-common
- All -> Spring as IOC and boot start

# Servlet 3.0
- no web.xml required

#Log setting
- in all the application.properties files.

# Compile
- checkout the code from Git.
- at unico-parent folder run
- mvn clean install
- then all module would be compiled.

# Assumption
- No SSL install or testing.
- No Unit test code inside the source.
- It allow many concurrent user. It depend on the CPU power only.

# System dependence
- MySQL server
- ActiveMQ as JMS broker
- Tomcat or All Server

# Testing tools
- soapui

# Local test on Tomcat
- open module soapWEB, restfulWEB, jmsClient module. type mvn spring-boot:run

# Enterprise system requirement
- the code can enhance to use external properties file for DB connection pool setting. Or use the J2EE server DB connection pool.

# Application server setting
- Download TOM-EE
- modify the tomee.xml file to enable the apps folder
- use maven to compile the code 
- deploy the EAR.ear file from <Parent>/EAR/target/EAR.ear to apps folder
- restart the TOME-EE server.

#DB Schema
```
CREATE TABLE `Numbers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first` int(11) NOT NULL,
  `second` int(11) NOT NULL,
  `gcd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
```


