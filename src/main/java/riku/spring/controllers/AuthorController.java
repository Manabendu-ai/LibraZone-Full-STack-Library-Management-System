package riku.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import riku.spring.models.Authors;
import riku.spring.services.AuthorService;

@Component
@Scope("prototype")
public class AuthorController {

    private AuthorService service;

    public AuthorService getService() {
        return service;
    }

    @Autowired
    public void setService(AuthorService service) {
        this.service = service;
    }

    public void addAuthor(Authors author){
        if(service.addAuthor(author)){
            System.out.println(author+" Added Into Database!");
            return;
        }
        System.out.println("Book Could not be Added!");
    }
}
