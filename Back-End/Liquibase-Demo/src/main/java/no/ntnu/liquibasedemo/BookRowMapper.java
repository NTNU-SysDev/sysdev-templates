package no.ntnu.liquibasedemo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int index) throws SQLException {
        return new Book(
                rs.getInt("id"),
                rs.getString("author"),
                rs.getString("title"),
                rs.getInt("year")
        );
    }
}
