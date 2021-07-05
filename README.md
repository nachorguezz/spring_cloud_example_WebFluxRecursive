# Spring Cloud Example

## Config Server, Eureka Server, Gateway and Microservice (WebFlux + MongoDB + Docker)

In this example we implemented Spring Cloud Gateway and got the microservices deployment url using Netflix Eureka Discovery Service.  
All the configuration were stored in a config server using Spring Cloud Config Server. 
<br/>

It is necessary to run the modules in the next order for the architecture to work correctly:
1. Config Server
2. Discovery Service
3. Gateway Service
4. Node Service

More information can be found in the README.md of each module, especially in the node microservice.  
All modules should be imported as a maven projects.
