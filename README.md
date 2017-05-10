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
## restfulWEB
- It is a web module.
- To handle all the restful request and fire JMS message to broker.(broker now define as activeMQ.
- the restful server provided two operation. that is push and list.
- the push will return SUCCESS or error text message
- the list will provide a list of JSON array. or empty array if no input in the DB.
- Technology used spring-boot, spring-restful, spring-jms.
## jmcClient
- It is a web module.
- To listen the JMS message from broker. It can more then one instance as JMS queue will handle the order. It is first come first serve. 
- Then it calculate the GCB value.
- Finally it store the content to databases. It has a ID managed by DB. So that ID can managed the order of input from user. 
- Technology used spring-boot, spring-jms, spring-jdbc.
## soapWEB
- It is a web module.
- It open a end point for use to accept the soap WS.
- The wsdl at location http://localhost:port/soapWEB/ws/numbers.wsdl
- it provided three operations to handle all the soap request.
- The gcd operation provide the head GCD value from the list in DB in xml format. if the head is empty. it will return -1. Once the GCD read. the content of that GCD will be remove from DB.
- The sum operator provide the sum of all GCD in the DB.
- The list gcd operator provide all the GCD value in DB.
- Technology used spring-boot, spring-ws, spring-jdbc.
## unico-common
- A jar module.
- It store the GCDCalculator, DAO interface, DAO Impl, Entity class or DTO.
- Technology used spring-ioc. So that the other module can use the component scan on the package to get the service from that jar.

# Software dependence
- EAR -> restfulWEB, soapWEB, jmsClient
- restfulWEB -> unico-common
- soapWEB -> unico-common
- jmsClient -> unico-common
- All -> Spring as IOC and boot start

# Servlet 3.0
- no web.xml required

# Log setting
- in all the application.properties files.

# Compile
- checkout the code from Git.
- at unico-parent folder run
- mvn clean install
- then all module would be compiled, output under target/*.war or target/*.ear.

# Assumption
- No SSL install or testing.
- No Unit test code inside the source.
- It allow many concurrent user. It depend on the CPU power only.
- The soap ws output if that is -1 means the list is empty.

# System dependence
- MySQL server
- ActiveMQ as JMS broker
- Tomcat or TOM-EE server 

# Testing tools
- soapui

# Local test on standalone machine.
- open module soapWEB, restfulWEB, jmsClient module. type mvn spring-boot:run

# Enterprise system requirement
- the code can enhance to use external properties file for DB connection pool setting. Or use the J2EE server DB connection pool.
- As different J2EE server has their own implementation. so that some of the 3rd party lib may affect the deployment or system startup. So that different App server should be some fine turn in order to reduce the problems from the internal design.
- success to deploy the code in cloud as that is cloud native design, microservice. 

# Application server setting
- Download TOM-EE
- modify the tomee.xml file to enable the apps folder.
- create a apps folder if need
- use maven to compile the code 
- deploy the EAR.ear file from {Parent}/EAR/target/EAR.ear to {TOM-EE}/apps folder
- restart the TOME-EE server.

# DB Schema
```
CREATE TABLE `Numbers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first` int(11) NOT NULL,
  `second` int(11) NOT NULL,
  `gcd` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
```

# Test case for soapUI
- Before test. Please made sure the activeMQ is up and run. TOM-EE are start the no error on their log.
- REST*.xml
- SOAP*.xml
- TestGuide.docx


