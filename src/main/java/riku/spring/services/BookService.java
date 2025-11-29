package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.Books;
import riku.spring.repository.BookRepo;

import java.util.List;

@Service
@Scope("prototype")
public class BookService {

    private BookRepo repo;

    public BookRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(BookRepo repo) {
        this.repo = repo;
    }

    public boolean addBook(Books book) {
        return repo.save(book);
    }

    public List<Books> getBooksByGerne(String gerne){
        return repo.getBooksByGerne(gerne);
    }

    public List<Books> getAllBooks(){
        return repo.getBooks();
    }

    public Books getBooksById(int id){
        return repo.getBookById(id);
    }

    public boolean updateBooksById(int total_copies, int avail_copies, int id){
        return repo.updateBooksById(total_copies, avail_copies, id);
    }
}
