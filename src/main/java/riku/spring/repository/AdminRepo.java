package riku.spring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import riku.spring.models.Admin;

import java.util.List;

@Repository
public class AdminRepo {

    private JdbcTemplate jdbc;

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Admin authenticateAdmin(Admin admin){
        String query = """
                SELECT * FROM admin
                WHERE
                    id = ? AND password = ?
                """;

        RowMapper<Admin> mapper = adminMapper();
        List<Admin> admins = jdbc.query(query, mapper, admin.getId(), admin.getPassword());

        if(admins.isEmpty()){
            return null;
        }
        return admins.get(0);

    }

    public boolean addAdmin(Admin admin){
        String query = """
                INSERT INTO admin
                (id, name, password, email)
                VALUES
                (?, ?, ?, ?);
                """;
        return jdbc.update(
                query,
                admin.getId(),
                admin.getName(),
                admin.getPassword(),
                admin.getEmail()
                )>0;

    }

    public RowMapper<Admin> adminMapper(){
        return (rs, rowNum)->{
            Admin admin = new Admin();
            admin.setId(rs.getInt("id"));
            admin.setName(rs.getString("name"));
            admin.setPassword(rs.getString("password"));
            admin.setEmail(rs.getString("email"));
            return admin;
        };
    }

    public Admin getAdminById(int id) {
        String query = """
                SELECT * FROM admin
                WHERE
                    id = ?
                """;

        RowMapper<Admin> mapper = adminMapper();
        List<Admin> admins = jdbc.query(query, mapper, id);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(0);
    }

    public Admin getAdminByuserNameId(String username) {
        String query = """
                SELECT * FROM admin
                WHERE
                    id = ?
                """;

        RowMapper<Admin> mapper = adminMapper();
        List<Admin> admins = jdbc.query(query, mapper, username);
        if(admins.isEmpty()){
            return null;
        }
        return admins.get(0);
    }
}
