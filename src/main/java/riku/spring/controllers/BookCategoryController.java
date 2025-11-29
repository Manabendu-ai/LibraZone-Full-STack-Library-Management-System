package riku.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import riku.spring.models.BookCategory;
import riku.spring.services.BookCategoryService;

@Controller
public class BookCategoryController {

    private BookCategoryService service;

    public BookCategoryService getService() {
        return service;
    }

    @Autowired
    public void setService(BookCategoryService service) {
        this.service = service;
    }

    public void addBookCategory(BookCategory bc){
        if(service.addBookCategory(bc)){
            System.out.println(bc + " Logged Into Database!");
            return;
        }
        System.out.println("Log Failed!");
    }
}
