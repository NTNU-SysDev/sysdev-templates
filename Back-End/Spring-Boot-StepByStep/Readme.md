# Step-by-step Java Spring boot tutorial. 

This is a tutorial showing Spring boot basics. Each commit implements one step.

The tutorial will go through all the steps for a simple application: book register. 
Books will be stored in a MySQL database. 
Backend will expose several endpoints: 
* Add a book
* Delete a book, 
* Update a book
* List all books
* Delete all books

In addition, there will be a frontend app that will interact with the backend. 
Frontend app is served by the same Spring boot project. 
It contains static HTML, Javascript and CSS files. These form an "empty page". 
Frontend app loads the data from backend adds it to the frontend. 
There are many options when it comes to developing a frontend app. 
In many cases it is a separate project. It has become popular to create frontend apps 
using Javascript solutions, with UI libraries such as ReactJS. 
No matter what you choose for frontend in your projects, the principles will be 
the same - Static HTML/JS files are loaded in the browser, these send data 
requests to the backend and fill in the data in the frontend app.

## Getting started
* Read the steps described below
* You can check out specific GIT commits marked with comment "tutorial-step-##". Open the Spring boot project in IntelliJ (or Netbeans).
* Build the project. First time the building will take some time to install all the dependencies.
* Run the project. It will start a Web-server on port 8080. You should be able to access all the REST enpoints at http://localhost:8080/...
* To use the MySQL database, you have to create a MySQL database yourself. For creating the tables and test data you can use the `setup.sql` script in this directory
* Configure Spring boot app to get correct MySQL access: take a copy of `src/resources/application.properties.template` file, store it as `src/resources/application.properties`, set the correct DB access values.

## Steps implemented in the tutorial
1. Set up a Spring-boot backend application that responds with simple "Hello" to a URL /hello.
2. Set up Postman test script with a test for the Hello endpoint. Add Postman script file to GIT.
3. Add a BookController with a single method listBooks(). It returns a static text in response to HTTP GET request /books/list: "No books currently in the register".
4. Create a test for it in Postman.
5. Add Book entity class. It will hold information about a single book.
6. Make BookController.listBooks() return a static list of books. Spring Boot will convert the List<Book> automatically to a JSON array. Update Postman test.
7. Create MySQL database that can store a list of books. Create a SQL setup script, add some test entries.
8. Create Repository class in the Spring application, configure it properly to connect to the database, using `src/resources/application.properties.template` file in git. Add `src/resources/application.properties` file to `.gitignore`.
9. Make the BookController.listBooks() return a list of books that is retrieved with SQL SELECT query from the database. Update Postman test.
10. Add another endpoint: BookController.clear(). It responds to HTTP DELETE request /books/clear. It deletes all books in the MySQL database and returns 200 OK when some books were deleted, 400 Bad request when there are not books to delete.
11. Add another endpoint in the same BookController: addBook(). It responds to a HTTP PUT /books/add request and takes in book data as JSON body. It responds with HTTP OK if the book was created, and returns bookId in the response body. If an error occurred, it responds with 400 Bad request and returns error message in the response body.
12. Create a re-entrant Postman test scenario (a test that we can run again and again): clear the books, select the books, add a book, get list of books again, add one more book, select books, then try to clear, select, try to clear again and get an error. 
13. Add a REST endpoint for deleting a book: BookController.deleteBook(). It responds to HTTP DELETE /books/delete, takes book ID. It responds with 200 OK on success, 400 Bad request on error (contains error message in the response body). Create a Postman test for it.
14. Add a REST endpoint for updating a book: BookController.updateBook(). It responds to HTTP POST /books/update, takes book ID from the URL and book data as JSON in the request body. It responds with 200 OK on success, 400 Bad request on error (contains error message in the response body). Create a Postman test for it.
15. The rest of the steps will concern creating a frontend app. First, create a static index.html file with "Hello world".
16. Add an empty table to the index.html file. It will list all books in the book register. But currently it has no data.
17. Load books in the frontend app: send an HTTP GET request to /books/list, parse the JSON response, add a table row for each book (display the books in the table).
18. Add an "Add book" form in the frontend app. When "Submit" button is clicked, send HTTP PUT request to /books/add . Parse the response. On error, show the error message. On success, refresh the page.
19. Add a "Delete" button to each row in the book table. When pressed, send HTTP DELETE request to /books/delete . Parse the response. On error, show the error message. On success, refresh the page.
20. Challenge for you: implement "Update functionality" in the frontend app. There can be several ways how one can have the update interface. For example, when user clicks in book title, show an input field with a "Change" button. When the user enters a new title in the input field and presses "Update" button, send request to the backend. Or you could have a form that pops up with all the book fields. User can enter the book information in the form and press "Submit". You can decide which format you like best.

# Individual challenge for a workshop
One does not learn much by just reading documentation. The real learning starts when you WRITE some code. Therefore, a challenge for you: Create a backend + frontend solution on your own. You can follow the described steps, but use a different data model. For example, instead of books, the database could contain a list of cars. Don't make it too complicated, because the most of the learning is going through the basic steps. Once you know how to handle a single HTTP POST and HTTP GET request, and how to write a single SQL query that returns and object (or a list of objects), you can easily create other HTTP GET/POST requests and other SQL queries.