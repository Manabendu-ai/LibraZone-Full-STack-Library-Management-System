package riku.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import riku.spring.models.Books;
import riku.spring.models.Issues;
import riku.spring.models.Members;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class IssueRepo {

    private JdbcTemplate jdbc;
    private BookRepo bookRepo;
    private MemberRepo memberRepo;

    public MemberRepo getMemberRepo() {
        return memberRepo;
    }

    @Autowired
    public void setMemberRepo(MemberRepo memberRepo) {
        this.memberRepo = memberRepo;
    }

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

//    public boolean save(Issues issue){
//        String query = """
//                INSERT INTO issue
//                (book_id, mem_id, issue_date, due_date, return_date, fine)
//                VALUE
//                (?,?,?,?,?,?);
//                """;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate issue_date = LocalDate.parse(issue.getIssue_date(),formatter);
//        LocalDate due_date = LocalDate.parse(issue.getDue_date(),formatter);
//        LocalDate return_date = LocalDate.parse(issue.getReturn_date(),formatter);
//
//        int rows;
//        try {
//            rows = jdbc.update(
//                    query,
//                    issue.getBook().getBook_id(),
//                    issue.getMember().getMem_id(),
//                    Timestamp.valueOf(issue_date.atStartOfDay()),
//                    Timestamp.valueOf(due_date.atStartOfDay()),
//                    Timestamp.valueOf(return_date.atStartOfDay()),
//                    issue.getFine()
//            );
//        } catch (Exception e){
//            return false;
//        }
//        return rows>0;
//    }

    public List<Issues> getIssues(){
        String query = """
                SELECT * FROM issue;
                """;

        RowMapper<Issues> mapper = getIssuesMapper();

        List<Issues> issues = jdbc.query(query, mapper);

        if(!issues.isEmpty()){
            return issues;
        }
        return null;
    }

    public Issues getIssueById(int id){
        String query = """
                SELECT * FROM issue WHERE id = ?;
                """;

        try {
            List<Issues> is = jdbc.query(query, getIssuesMapper(), id);
            return is.isEmpty() ? null : is.get(0);
        } catch (Exception e){
            return null;
        }
    }

    public Issues getIssueByBookId(int id){
        String query = """
                SELECT * FROM issue WHERE book_id = ?;
                """;

        try {
            List<Issues> is = jdbc.query(query, getIssuesMapper(), id);
            return is.isEmpty() ? null : is.get(0);
        } catch (Exception e){
            return null;
        }
    }

    public Issues getIssueByMemId(int id){
        String query = """
                SELECT * FROM issue WHERE mem_id = ?;
                """;

        try {
            List<Issues> is = jdbc.query(query, getIssuesMapper(), id);
            return is.isEmpty() ? null : is.get(0);
        } catch (Exception e){
            return null;
        }
    }

    public List<Issues> getIssueByMemberName(String name){
        String query = """
                SELECT issue.* FROM issue
                JOIN members
                ON issue.mem_id = members.id
                WHERE members.name like ?;
                """;

        name = "%"+name+"%";
        return jdbc.query(query,getIssuesMapper(), name);
    }

    public List<Issues> getIssueByBookName(String name){
        String query = """
                SELECT issue.* FROM issue
                JOIN books
                ON issue.book_id = books.book_id
                WHERE books.title like ?;
                """;

        name = "%"+name+"%";
        return jdbc.query(query,getIssuesMapper(), name);
    }

    private RowMapper<Issues> getIssuesMapper() {
        return (rs, rowNum) -> {
            Issues is = new Issues();
            is.setId(rs.getInt("id"));
            is.setBook(
                    bookRepo.getBookById(rs.getInt("book_id"))
            );
            is.setMember(
                    memberRepo.getMembersById(rs.getInt("mem_id"))
            );
            is.setIssue_date(String.valueOf(rs.getDate("issue_date")));
            is.setDue_date(String.valueOf(rs.getDate("due_date")));
            try {
                java.sql.Date return_date = rs.getDate("return_date");
                if(return_date!=null){
                    is.setReturn_date(String.valueOf(return_date));
                    if(setDueDate(rs.getInt("id"))){
                        is.setFine(is.calculateFine(is.getDue_date(),is.getReturn_date()));
                    }
                } else{
                    is.setReturn_date(null);
                    is.setFine(0.0);
                }

            } catch (Exception e){
                is.setReturn_date(null);
                is.setFine(0.0);
            }

            return is;
        };
    }

    public boolean save(Issues issues, int days){
        int book_id = issues.getBook().getBook_id();
        int mem_id = issues.getMember().getMem_id();

        if(isValidBook(book_id) && isValidMember(mem_id)){
            String query =
                    """
                        INSERT INTO issue
                        (book_id, mem_id)
                        VALUES
                        (?,?);
                    """;
            int rows = jdbc.update(query, book_id, mem_id);
            if(rows > 0){
                query = """
                        UPDATE issue
                        SET due_date = DATE_ADD(issue_date, INTERVAL ? DAY)
                        WHERE id = ?;
                        """;
                int id = getLatestIssueId();

                rows = jdbc.update(query, days, id);

                return rows > 0;
            }
            return false;
        }
        return false;
    }

    public boolean setDueDate(int id){
        String query = """
                        UPDATE issue
                        SET due_date = DATE_ADD(issue_date, INTERVAL 28 DAY)
                        WHERE id = ?;
                        """;

        int rows = jdbc.update(query, id);
        return rows>0;
    }

    public int getLatestIssueId(){
        String query = """
                SELECT id FROM issue
                ORDER BY id DESC LIMIT 1;
                """;
        RowMapper<Integer> mapper = (rs, rowNum) -> rs.getInt("id");
        return jdbc.query(query,mapper).get(0);
    }

    public boolean isValidBook(int id){
        return bookRepo.getBookById(id) != null;
    }

    public boolean isValidMember(int id){
        return memberRepo.getMembersById(id) != null;
    }
}
