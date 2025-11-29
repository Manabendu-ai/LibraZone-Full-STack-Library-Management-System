package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.BookCategory;
import riku.spring.repository.BookCategoryRepo;

@Service
@Scope("prototype")
public class BookCategoryService {

    private BookCategoryRepo repo;

    public BookCategoryRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(BookCategoryRepo repo) {
        this.repo = repo;
    }

    public boolean addBookCategory(BookCategory bc){
        return repo.save(bc);
    }

    public BookCategory getBookCatbyBookId(int id){
        return repo.getBookCatbyBookId(id);
    }

    public boolean updateBookCatById(int cat_id, int book_id){
        return repo.updateBookCatById(cat_id, book_id);
    }
}
