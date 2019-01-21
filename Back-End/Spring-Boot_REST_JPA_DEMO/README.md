# Spring-boot REST backend

This project contains a backend application built using Spring-boot, which makes it simpler and faster to setup a Spring application 
that you can "just run". In this application we also use JPA which is an API that helps us manage relational data in applications.

## Setting up your own Spring-boot project

It is fairly simple to set up your own spring-boot project. The easiest way is to use this tool: https://start.spring.io<br />
Some IDE's also have their own Spring project setup integrated. Spring also have great guides to help you start using spring-boot.
Can be found here: https://spring.io/guides

## Getting Started

To start using this project you simply have to press the "run" button in your IDE. Spring-boot has tomcat embedded inside so you don't need
to setup your own environment to run the application.<br />By default the project has the external url set to http://dev.libane.tk/users<br />
This is a server we have set up that has a MySQL database running. This server also runs this application.
If you want to connect it to your own database simply edit the database location, 
username and password in the `application.properties` file.

## Good to know annotations
* [@SpringBootApplication](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/api/org/springframework/boot/autoconfigure/SpringBootApplication.html) - 
Indicates a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning.
* [@RestController](https://docs.spring.io/spring/docs/5.0.4.BUILD-SNAPSHOT/javadoc-api/org/springframework/web/bind/annotation/RestController.html) - 
Types that carry this annotation are treated as controllers where @RequestMapping methods assume @ResponseBody.
* [@Service](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html) - 
We mark beans with @Service to indicate that it’s holding the business logic
* [@Component](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html) - 
We can use @Component across the application to mark the beans as Spring’s managed components
* [@Autowired](https://www.baeldung.com/spring-autowire) - @Autowired can be used in many different scenarios so i recommend you read on the linked guide
