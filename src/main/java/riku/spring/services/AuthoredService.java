package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.Authored;
import riku.spring.models.Books;
import riku.spring.repository.AuthoredRepo;

import java.util.List;

@Service
@Scope("prototype")
public class AuthoredService {

    private AuthoredRepo repo;

    public AuthoredRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(AuthoredRepo repo) {
        this.repo = repo;
    }

    public boolean addAuthored(Authored authored){
        return repo.save(authored);
    }

    public List<Authored> getBooksByAuthor(String author) {
        return repo.getBooksByAuthor(author);
    }

    public List<Authored> getAllAuthored(){
        return repo.getAuthoredList();
    }

    public List<Authored> getBooksByGerne(String gerne){
        return repo.getBooksByGerne(gerne);
    }

    public List<Authored> getBooksByTitle(String title) {
        return repo.getBooksByTitle(title);
    }

    public Authored getBooksById(int id){
        return repo.getBooksById(id);
    }
}
