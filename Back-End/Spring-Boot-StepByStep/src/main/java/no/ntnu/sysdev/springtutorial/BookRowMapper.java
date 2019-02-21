package no.ntnu.sysdev.springtutorial;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Translates one row from SQL result set into a Book object
 */
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowIndex) throws SQLException {
        return new Book(rs.getString("author"), rs.getString("title"));
    }
}
