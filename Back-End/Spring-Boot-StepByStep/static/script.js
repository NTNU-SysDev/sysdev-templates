// Sends HTTP request to backend, loads a list of books
function loadBooks() {
    console.log("Loading books...");
    fetch("/books/list").then(function(response) {
       return response.json();
    }).then(function (books) {
        console.log("Book data: ", books);
        if (Array.isArray(books)) {
            for (var i = 0; i < books.length; i++) {
                var book = books[i];
                showBook(book);
            }
        }
    });
}

// Shows one book in the table
function showBook(book) {
    var table = document.getElementById("data-table");
    var row = document.createElement("tr");
    appendCell(book.id, row);
    appendCell(book.author, row);
    appendCell(book.title, row);

    table.appendChild(row);
}

// Adds one cell to the data table
function appendCell(val, row) {
    var cell = document.createElement("td");
    cell.innerText = val;
    row.appendChild(cell);
}
