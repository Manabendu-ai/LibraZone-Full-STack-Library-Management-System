package riku.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import riku.spring.models.Authored;
import riku.spring.models.Authors;
import riku.spring.models.Books;

import java.util.List;

@Repository
public class AuthorRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean save(Authors author){
        String query = """
                INSERT INTO authors
                (author_id, name)
                VALUES
                (?,?);
                """;
        int rows;
        try {
            rows = jdbc.update(
                    query,
                    author.getAuthor_id(),
                    author.getAuthor_name()
            );
        } catch (Exception e){
            return false;
        }
        return rows > 0;
    }

    public Authors getAuthorsById(int id){
        String query = """
                SELECT * FROM authors
                WHERE author_id = ?;
                """;

        RowMapper<Authors> mapper = getAuthorMapper();

        List<Authors> authors = jdbc.query(query, mapper, id);
        if(!authors.isEmpty()){
            return authors.get(0);
        }
        return null;
    }

    public List<Authors> getAuthors(){
        String query = """
                SELECT * FROM authors;
                """;

        RowMapper<Authors> mapper = getAuthorMapper();

        List<Authors> authors = jdbc.query(query, mapper);
        if(!authors.isEmpty()){
            return authors;
        }
        return null;
    }

    private RowMapper<Authors> getAuthorMapper() {
        return (rs, rowNum) -> {
            Authors au = new Authors();
            au.setAuthor_id(rs.getInt("author_id"));
            au.setAuthor_name(rs.getString("name"));
            return au;
        };
    }
}
