package no.ntnu.sysdev.springtutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/books/list")
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    @RequestMapping(value = "/books/clear", method = RequestMethod.DELETE)
    public ResponseEntity<String> clearBooks() {
        boolean deleted = bookRepository.clear();
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/books/delete/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        String error = bookRepository.delete(bookId);
        if (error == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.PUT)
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        String error = bookRepository.add(book);
        if (error == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }
}
