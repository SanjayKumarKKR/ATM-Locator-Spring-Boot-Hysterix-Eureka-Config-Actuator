# ATM-Locator-Spring-Boot-Hysterix-Eureka-Config-Actuator

 A Distributed Resilient Application to locate ATMs using Spring Cloud Netflix with Eureka (Service Discovery), Hystrix (Circuit Breaker), Spring Cloud Config (externalized configuration).

How it was Implemented ?

Utilized spring initializer project to generate the basic code structure and architecture of the application.
- Added Spring Cloud Dependencies with Eureka, Config, Hystrix and Actuator.
- Configured Resttemplate calls with by-passing ssl for consuming ING web service ( https://www.ing.nl/api/locator/atms/ )
- The ING web-service provides a malformed json response, containing a few garbage characters in the beginning, this was adjusted in the api and proper json response is parsed to populate Data Transfer Object's.
- There is two Spring Profiles i.e. default and Security which has more Security built when passing ssl for consuming ING web service. 

How is the Architecture Designed ?

A basic spring MVC design : We have designed the architecure using Three MicroServices i.e. ATM-Locator-Spring-Boot, Eureka-Server and Spring-Cloud-Server.  Request Callstack : Controller -> Service -> Repository and vice a versa for response.

Controllers :
AtmController.java : exposes 2 rest api's 
- /locations : Lists all the atm addresses exposed by ING atm locator service as a proper JSON respone.
- /locations/{city} : Filters and lists all locations based on provided city as a proper JSON response.
- /postalCode/{postalCode} : Filters and lists all locations based on provided postalCode as a proper JSON response.

Services :
AtmLocator.java : implements business logic behind the exposed web services utilizing output from repository.

Repositories :
AtmDataPopulator.java : utilizes spring rest-template for consuming the ING ATM locator service.

Tools used :
- Maven
- JDK 8
- Spring boot
- Tomcat 7

How to Run ?
maven should be installed.

- clone the repo.
- mvn clean install 
- Deploy to Tomcat 7

OR
- clone the repo.
- cd /ATM-Locator-Spring-Boot
- mvn clean spring-boot:run
