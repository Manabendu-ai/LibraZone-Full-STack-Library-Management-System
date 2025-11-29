package riku.spring.repository;

import org.apache.el.parser.AstFalse;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import riku.spring.models.Books;

import java.util.List;

@Repository
public class BookRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean save(Books book) {
        String query = """
                INSERT INTO books
                (book_id, title, total_copies, available_copies)
                VALUES
                (?,?,?,?);
                """;
        int rows;
        try{
            rows = jdbc.update(
                query,
                book.getBook_id(),
                book.getTitle(),
                book.getTotal_copies(),
                book.getAvailable_copies()
            );
        }   catch (Exception e){
            return false;
        }
        return rows >0;
    }

    public List<Books> getBooksByGerne(String gerne) {
        String query = """
                SELECT * FROM books
                JOIN book_category as bc
                ON books.book_id = bc.book_id
                JOIN categories as cat
                ON cat.cat_id = bc.cat_id
                WHERE cat.gerne like ?;
                """;
        String g = "%"+gerne+"%";

        RowMapper<Books> mapper = getBookMapper();

        List<Books> books = jdbc.query(query, mapper, g);
        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    public Books getBookById(int id){
        String query = """
                SELECT * FROM books
                WHERE book_id = ?;
                """;

        RowMapper<Books> mapper = getBookMapper();

        List<Books> books = jdbc.query(query, mapper, id);

        if(!books.isEmpty()){
            return books.get(0);
        }
        return null;
    }

    public List<Books> getBooks(){
        String query = """
                SELECT * FROM books;
                """;

        RowMapper<Books> mapper = getBookMapper();

        List<Books> books = jdbc.query(query, mapper);
        if(!books.isEmpty()){
            return books;
        }
        return null;
    }

    public boolean updateBooksById(int total_copies, int avail_copies, int id){
        String query = """
                UPDATE books
                SET total_copies = ?, available_copies = ?
                WHERE book_id = ?;
                """;
        int rows;
        try{
            rows=jdbc.update(query, total_copies, avail_copies, id);
        } catch (Exception e){
            return false;
        }
        return rows>0;
    }

    public RowMapper<Books> getBookMapper(){
        return (rs, rowNum)->{
            Books bk = new Books();
            bk.setBook_id(rs.getInt("book_id"));
            bk.setTitle(rs.getString("title"));
            bk.setTotal_copies(rs.getInt("total_copies"));
            bk.setAvailable_copies(rs.getInt("available_copies"));
            return bk;
        };
    }
}
