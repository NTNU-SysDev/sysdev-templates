package no.ntnu.liquibasedemo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LibraryRowMapper implements RowMapper<Library> {
    @Override
    public Library mapRow(ResultSet rs, int index) throws SQLException {
        return new Library(rs.getInt("id"), rs.getString("name"));
    }
}
