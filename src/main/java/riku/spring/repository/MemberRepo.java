package riku.spring.repository;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;
import riku.spring.models.Members;

import java.util.List;

@Repository
public class MemberRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean save(Members member){
        String query = """
                INSERT INTO members
                (id, name, phone, email)
                VALUES
                (?,?,?,?);
                """;
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int rows;
         try {
             rows = jdbc.update(
                     query,
                     member.getMem_id(),
                     member.getMem_name(),
                     member.getPhone(),
                     member.getEmail()
//                     Timestamp.valueOf(LocalDate.parse
//                                     (member.getJoined(), formatter)
//                             .atStartOfDay()
//                     )
             );
         } catch (Exception e){
             return false;
         }
         return rows > 0;
    }

    public List<Members> getMembers(){
        String query = """
                SELECT * FROM members;
                """;

        RowMapper<Members> mapper = getMembersRowMapper();

        List<Members> members = jdbc.query(query, mapper);
        if(!members.isEmpty()){
            return members;
        }
        return null;
    }

    public Members getMemberByName(String name){
        String query = """
                SELECT * FROM members WHERE name like ?;
                """;

        String input = "%"+name+"%";
        RowMapper<Members> mapper = getMembersRowMapper();

        List<Members> members = jdbc.query(query, mapper, input);

        if(!members.isEmpty()){
            return members.get(0);
        }
        return null;
    }

    public Members getMembersById(int id){
        String query = """
                SELECT * FROM members WHERE id = ?;
                """;

        RowMapper<Members> mapper = getMembersRowMapper();

        List<Members> members = jdbc.query(query, mapper, id);

        if(!members.isEmpty()){
            return members.get(0);
        }
        return null;
    }

    private RowMapper<Members> getMembersRowMapper() {
        return (rs, rowNum) -> {
            Members mem = new Members();
            mem.setMem_id(rs.getInt("id"));
            mem.setMem_name(rs.getString("name"));
            mem.setPhone(rs.getLong("phone"));
            mem.setEmail(rs.getString("email"));
            return mem;
        };
    }

    public boolean updateMemberById(Members mem) {
        String query = """
                UPDATE members
                SET name = ?, phone = ?, email = ?
                WHERE id = ?
                """;

        int rows;
        try{
            rows = jdbc.update(
                    query,
                    mem.getMem_name(),
                    mem.getPhone(),
                    mem.getEmail(),
                    mem.getMem_id()
            );
        } catch (Exception e){
            return false;
        }
        return rows > 0;
    }
}
