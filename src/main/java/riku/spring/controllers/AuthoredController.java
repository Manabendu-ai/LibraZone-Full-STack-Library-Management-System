package riku.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import riku.spring.models.Authored;
import riku.spring.models.Authors;
import riku.spring.models.BookCategory;
import riku.spring.models.Books;
import riku.spring.services.AuthorService;
import riku.spring.services.AuthoredService;
import riku.spring.services.BookCategoryService;
import riku.spring.services.BookService;

import java.util.List;

@Controller
@Scope("prototype")
public class AuthoredController {

    private AuthoredService service;
    private BookService bookService;
    private AuthorService authorService;
    private BookCategoryService catService;

    public BookService getBookService() {
        return bookService;
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public BookCategoryService getCatService() {
        return catService;
    }

    @Autowired
    public void setCatService(BookCategoryService catService) {
        this.catService = catService;
    }

    public AuthoredService getService() {
        return service;
    }

    @Autowired
    public void setAuthoredService(AuthoredService authored) {
        this.service = authored;
    }

    public void addAuthored(Authored authored){
        if(service.addAuthored(authored)){
            System.out.println(authored+" Logged Into Database!");
            return;
        }
        System.out.println("Log Failed!");
    }
    public List<Authored> getBooksByAuthor(String author){
        return service.getBooksByAuthor(author);
    }

    @RequestMapping("/books")
    public String authored(Model model){
        List<Authored> auths = service.getAllAuthored();
        model.addAttribute("auths", auths);
        return "books";
    }

    @RequestMapping("/search")
    public String searchBooks(@RequestParam("search_book") String para,  Model model){
        List<Authored> auths = service.getBooksByGerne(para);
        if(auths == null) auths = service.getBooksByAuthor(para);
        if(auths == null) auths = service.getBooksByTitle(para);

        model.addAttribute("auths", auths);
        model.addAttribute("para", para);
        return "books";
    }

    @RequestMapping("/addBook")
    public String addBook(){
        return "addBooks";
    }

    @RequestMapping("/add_book_to_db")
    public String registerBook(
        @RequestParam("book_id") int book_id,
        @RequestParam("title") String title,
        @RequestParam("gerne") int gerne,
        @RequestParam("total_copies") int total_copies,
        @RequestParam("available_copies") int aval_copies,
        @RequestParam("author_id") int author_id,
        @RequestParam("author_name") String author_name,
        Model model
    ){
        Books book = new Books();
        book.setBook_id(book_id);
        book.setTitle(title);
        book.setTotal_copies(total_copies);
        book.setAvailable_copies(aval_copies);

        Authors author = new Authors();
        author.setAuthor_id(author_id);
        author.setAuthor_name(author_name);

        BookCategory cat = new BookCategory();
        cat.setBook(book);
        cat.setCat_id(gerne);

        Authored authored = new Authored();
        authored.setBook(book);
        authored.setAuthor(author);

        boolean b1 = bookService.addBook(book);
        boolean a1 = authorService.addAuthor(author);
        boolean c1 = catService.addBookCategory(cat);
        boolean at1 = service.addAuthored(authored);


        String msg = "";
        int invoked = 0;
        if (at1 && b1 && a1 && c1){
            msg = "Book Details Added Successfully!";
        }
        invoked++;
        model.addAttribute("invoked",invoked);
        model.addAttribute("msg", msg);
        return "addBooks";
    }

}
