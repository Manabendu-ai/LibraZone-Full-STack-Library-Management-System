package riku.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import riku.spring.models.Authored;
import riku.spring.models.BookCategory;
import riku.spring.models.Books;

import java.util.List;

@Repository
public class BookCategoryRepo {

    private JdbcTemplate jdbc;
    private BookRepo bookRepo;

    public BookRepo getBookRepo() {
        return bookRepo;
    }

    @Autowired
    public void setBookRepo(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    public JdbcTemplate getJdbc() {
        return jdbc;
    }
    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean save(BookCategory bc){
        String query = """
                INSERT INTO book_category
                (cat_id, book_id)
                VALUES
                (?,?);
                """;
        int rows;
        try {
            rows = jdbc.update(
                    query,
                    bc.getCat_id(),
                    bc.getBook().getBook_id()
            );
        }catch (Exception e){
            return false;
        }
        return rows >0;
    }

    public BookCategory getBookCatbyBookId(int id){

        String query = """
                SELECT * FROM
                book_category as bc
                JOIN\s
                	books as bk\s
                    ON bc.book_id = bk.book_id
                WHERE
                	bk.book_id = ?;
                """;

        List<BookCategory> books = jdbc.query(query, bookCategoryRowMapper(), id);
        if(!books.isEmpty()){
            return books.get(0);
        }
        return null;
    }

    private RowMapper<BookCategory> bookCategoryRowMapper(){
        return (rs, rowNum)->{
            BookCategory bc = new BookCategory();
            bc.setBook(
                    bookRepo.getBookById(rs.getInt("book_id"))
            );
            bc.setCat_id(rs.getInt("cat_id"));
            return bc;
        };
    }

    public boolean updateBookCatById(int cat_id, int book_id){
        String query = """
                UPDATE book_category
                SET cat_id = ?
                WHERE book_id = ?;
                """;
        int rows;
        try{
            rows=jdbc.update(query, cat_id, book_id);
        } catch (Exception e){
            return false;
        }
        return rows>0;
    }
}
