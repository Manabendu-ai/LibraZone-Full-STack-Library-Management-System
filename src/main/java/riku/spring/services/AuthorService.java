package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.Authors;
import riku.spring.repository.AuthorRepo;

@Service
@Scope("prototype")
public class AuthorService {

    private AuthorRepo repo;

    public AuthorRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(AuthorRepo repo) {
        this.repo = repo;
    }

    public boolean addAuthor(Authors author){
        return repo.save(author);
    }
}
