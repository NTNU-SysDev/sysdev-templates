package no.ntnu.liquibasedemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryRepository {
    private final JdbcTemplate jdbcTemplate;
    private RowMapper<Library> rowMapper = new LibraryRowMapper();

    @Autowired
    public LibraryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Library> findAll() {
        return jdbcTemplate.query("SELECT * FROM library", rowMapper);
    }
}
