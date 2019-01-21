# Spring-boot REST backend

This project contains a backend application built using Spring-boot, which makes it simpler and faster to setup a Spring application 
that you can "just run". In this application we also use JDBC which is an API that helps us manage relational data in applications.

## Setting up your own Spring-boot project

It is fairly simple to set up your own spring-boot project. The easiest way is to use this tool: https://start.spring.io<br />
Some IDE's also have their own Spring project setup integrated. Spring also have great guides to help you start using spring-boot.
Can be found here: https://spring.io/guides

## Getting Started

To start using this project you simply have to press the "run" button in your IDE. Spring-boot has tomcat embedded inside so you don't need
to setup your own environment to run the application.<br />
To connect it to your own database simply edit the database location, 
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
* [@Repository](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Repository.html) - 
Defined as "a mechanism for encapsulating storage, retrieval, and search behavior which emulates a collection of objects".

## JDBC VS JPA
The biggest difference between the two is that with JDBC you have to manually map SQL types to java types, while JPA simplifies this.
The problem with JPA is that when you have complex queries and multiple tables and entity-objects, it will eventually become really
hard to familiarize/understand what the code does. With JDBC there is more code to write to set it up, but you'll get more freedom when you write your queries. So in smaller databases the easiest and fastest solution is probably to use JPA, but with bigger and more complex databases, you will gain more in the longer term by using JDBC.

Another thing to be aware off, (is also describer well here: https://stackoverflow.com/questions/11881548/jpa-or-jdbc-how-are-they-different/11881739) is that with JDBC you write your own queries, but with JPA the queries are usually generated "magically". So you typically don't write your own queries. Examples can be found in project `Spring-Boot_REST_JPA_DEMO` in the UserRepository interface. By extending our interface with JpaRepository these abstract methods will generate the approprate SQL query.
