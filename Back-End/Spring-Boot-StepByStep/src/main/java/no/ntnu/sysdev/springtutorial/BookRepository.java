package no.ntnu.sysdev.springtutorial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        SqlRowSet rs = jdbcTemplate.queryForRowSet("SELECT * FROM books");
        if (rs.first()) {
            Book b = new Book(rs.getString("author"), rs.getString("title"));
            books.add(b);
            while (rs.next()) {
                b = new Book(rs.getString("author"), rs.getString("title"));
                books.add(b);
            }
        }
        return books;
    }
}
