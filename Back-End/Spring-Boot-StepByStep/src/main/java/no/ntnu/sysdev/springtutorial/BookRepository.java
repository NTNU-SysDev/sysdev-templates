package no.ntnu.sysdev.springtutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Book> rowMapper = new BookRowMapper();

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", rowMapper);
    }

    /**
     * Delete all the books in the database.
     * @return true when some books were deleted, false if there were no books to delete.
     */
    public boolean clear() {
        int numRows = jdbcTemplate.update("DELETE FROM books");
        return numRows > 0;
    }

    /**
     * Add a book to the database
     * @param book
     * @return Return Error message. Null on success.
     */
    public String add(Book book) {
        String query = "INSERT INTO books (author, title) VALUES (?, ?)";
        try {
            int numRows = jdbcTemplate.update(query, book.getAuthor(), book.getTitle());
            if (numRows == 1) {
                return null;
            } else {
                return "Could not add new book";
            }
        } catch (DataAccessException e) {
            return "Book with such title already exists";
        } catch (Exception e) {
            return "Could not add new book: " + e.getMessage();
        }
    }
}
