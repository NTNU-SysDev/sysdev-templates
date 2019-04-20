# SQL versioning tutorial
This is a tutorial showing how to version-control your SQL database schema. 
We use [MySQL](https://www.mysql.com/) database and [Liquibase](http://www.liquibase.org) DB versioning tool.

The code is implemented in steps. Each step is made as a separate GIT commit.

If you are not familiar with Spring boot, check out the [Spring boot tutorial](https://github.com/NTNU-SysDev/sysdev-templates/tree/master/Back-End/Spring-Boot-StepByStep) first. 

## Getting started
* Read the steps described below
* You can check out specific GIT commits marked with comment "lbtut-step-##". 
Open the Spring boot project in IntelliJ (or Netbeans).
* Build the project. First time the building will take some time to install all the dependencies.
* Run the project. It will start a Web-server on port 8080. You should be able to access all the REST endpoints 
at http://localhost:8080/...
* To use the MySQL database, you have to create a MySQL database yourself. 
* Configure Spring boot app to get correct MySQL access: take a copy 
of `src/resources/application.properties.template` file, store it 
as `src/resources/application.properties`, set the correct DB access values.
* To update the database to the latest version, you can always run `mvn liquibase:update`. 


## Steps implemented in the tutorial

**Step 1:** Set up a Spring-boot backend application that responds with a list of all the books 
to a URL /books/list.

**Step 2:** Set up Liquibase SQL versioning tool and create the initial database version using it. 
From now on you can run `mvn liquibase:update` to update the database to the latest version. 
If you want to see the update SQL without actually updating the database, you can run `mvn liquibase:updateSQL`. 
That will create a file `target/liquibase/migrate.sql`. Note: if you don't have Maven installed on 
your machine, download it from [Apache Maven website](http://maven.apache.org/download.cgi) 
and set up PATH environment variable so that mvn command is found (here is [instruction for Windows](https://www.computerhope.com/issues/ch000549.htm)).  

**Step 3:** Add another Liquibase changeset that inserts some test data. 

**Step 4:** Create an update to the application and database as well: add Library registry listing 
all libraries to a URL /libraries/list . Create a new Liquibase changeset that updates the database
accordingly.

**Step 5: Extra challenge for you**: add a Liquibase changeset that will implement migration of 
the `author` column from a simple string column to a separate SQL table `author`. . You can use the following steps as a hint: 
* Create the `author` table. It must contain three columns: `id`, `firstname` and `lastname`.
* Update the `book` table: add a column `author_id`.
* Find author firstname and lastname by taking the `book.author` column and split it by space.
* Insert a new record with the firstname and lastname into the new `author` table.
* Update the `book` entries: set their `author_id` column.
* Drop the `book.author` column. 


