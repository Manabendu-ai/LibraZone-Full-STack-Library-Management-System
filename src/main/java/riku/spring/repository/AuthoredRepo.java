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
public class AuthoredRepo {

    private JdbcTemplate jdbc;
    private BookRepo bookRepo;
    private AuthorRepo authorRepo;

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    @Autowired
    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public AuthorRepo getAuthorRepo() {
        return authorRepo;
    }

    @Autowired
    public void setAuthorRepo(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean save(Authored authored){
        int book_id = authored.getBook().getBook_id();
        int author_id = authored.getAuthor().getAuthor_id();

        String query = """
                INSERT INTO authored
                (author_id, book_id)
                VALUES
                (?,?);
                """;
        int rows;
        try {
             rows = jdbc.update(
                    query,
                    author_id,
                    book_id
            );
        } catch (Exception e){
            return false;
        }
        return rows > 0;
    }

    public List<Authored> getAuthoredList(){
        String query = "SELECT * FROM authored;";
        RowMapper<Authored> mapper = authoredRowMapper();
        List<Authored> books = jdbc.query(query, mapper);

        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    public Authored getBooksById(int id){
        String query = """
                SELECT *
                FROM authored as atu
                JOIN
                	authors as at\s
                    ON atu.author_id = at.author_id
                JOIN
                	books as bk
                    ON atu.book_id = bk.book_id
                WHERE
                	bk.book_id = ?;
                """;
        RowMapper<Authored> mapper = authoredRowMapper();
        List<Authored> books = jdbc.query(query, mapper, id);
        if(!books.isEmpty()){
            return books.get(0);
        }
        return null;
    }

    public List<Authored> getBooksByAuthor(String author) {
        String query = """
                SELECT * FROM authored as au
                JOIN books
                ON books.book_id = au.book_id
                JOIN authors as at
                ON at.author_id = au.author_id
                WHERE at.name like ?;
                """;

        String a = "%"+author+"%";

        RowMapper<Authored> mapper = authoredRowMapper();

        List<Authored> books = jdbc.query(query, mapper, a);

        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    public List<Authored> getBooksByTitle(String title){
        String query = """
                SELECT * FROM
                authored
                   JOIN\s
                    	books ON authored.book_id = books.book_id
                    WHERE
                       	books.title like ?;
                """;

        String a = "%"+title+"%";

        RowMapper<Authored> mapper = authoredRowMapper();

        List<Authored> books = jdbc.query(query, mapper, a);

        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    public List<Authored> getBooksByGerne(String gerne){

        String query = """
                SELECT * FROM\s
                authored
                WHERE book_id\s
                	IN(
                		SELECT bk.book_id
                		FROM
                			books as bk
                		JOIN\s
                			book_category AS bc ON bc.book_id = bk.book_id
                		JOIN
                			categories AS cat ON bc.cat_id = cat.cat_id
                		WHERE
                			cat.cat_id in (
                				SELECT cat_id FROM categories WHERE gerne like ?
                			)
                );
                """;

        List<Authored> books = jdbc.query(query, authoredRowMapper(), gerne);
        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    private RowMapper<Authored> authoredRowMapper(){
        return  (rs, rowNum)->{
            Authored ak = new Authored();
            ak.setBook(
                    bookRepo.getBookById(rs.getInt("book_id"))
            );
            ak.setAuthor(
                    authorRepo.getAuthorsById(rs.getInt("author_id"))
            );
            return ak;
        };
    }
}
