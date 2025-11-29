package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riku.spring.models.Admin;
import riku.spring.repository.AdminRepo;

import java.util.Random;

@Service
public class AdminService {
    private AdminRepo repo;

    public AdminRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(AdminRepo repo) {
        this.repo = repo;
    }

    public Admin authAdmin(Admin admin){
        return repo.authenticateAdmin(admin);
    }

    public int generateId(){
        Random rnd = new Random();
        int base = 245999;
        int limit = 299999;
        int id = rnd.nextInt(base, limit);
        if(repo.getAdminById(id) != null){
            return generateId();
        }
        return id;
    }

    public boolean addAdmin(Admin admin){
        admin.setId(generateId());
        return repo.addAdmin(admin);
    }
}
