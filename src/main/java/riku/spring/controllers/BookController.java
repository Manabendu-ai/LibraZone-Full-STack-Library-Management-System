package riku.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import riku.spring.models.Authored;
import riku.spring.models.BookCategory;
import riku.spring.models.Books;
import riku.spring.services.AuthorService;
import riku.spring.services.AuthoredService;
import riku.spring.services.BookCategoryService;
import riku.spring.services.BookService;

import java.util.List;

@Controller
@Scope("prototype")
public class BookController {

    private AuthoredService at_service;
    private BookCategoryService bkCat_service;

    public BookCategoryService getBkCat_service() {
        return bkCat_service;
    }

    @Autowired
    public void setBkCat_service(BookCategoryService bkCat_service) {
        this.bkCat_service = bkCat_service;
    }

    public AuthoredService getAt_service() {
        return at_service;
    }

    @Autowired
    public void setAt_service(AuthoredService at_service) {
        this.at_service = at_service;
    }

    private BookService service;

    public BookService getService() {
        return service;
    }

    @Autowired
    public void setService(BookService service) {
        this.service = service;
    }

    public void addBook(Books book){
        if(service.addBook(book)){
            System.out.println(book+" Added Into the Database!");
            return;
        }
        System.out.println("Book Could not be Added!");
    }

    public List<Books> getBooksByGerne(String gerne){
        return service.getBooksByGerne(gerne);
    }

    @RequestMapping("/editBook")
    public String editBoot(
            @RequestParam("bookId") int book_id,
            Model model
    ){
        Authored atd = at_service.getBooksById(book_id);
        BookCategory bc = bkCat_service.getBookCatbyBookId(book_id);
        model.addAttribute("book", atd);
        model.addAttribute("cat_id", bc.getCat_id());
        return "editBookPage";
    }

    @RequestMapping("/update_book_to_db")
    public String update(
            @RequestParam("book_id") int book_id,
            @RequestParam("gerne") int cat_id,
            @RequestParam("total_copies") int total_copies,
            @RequestParam("available_copies") int available_copies,
            Model model
    ){

        boolean b1 = service.updateBooksById(total_copies, available_copies, book_id);
        boolean b2 = bkCat_service.updateBookCatById(cat_id, book_id);

        String msg = "";
        if (b1 && b2){
            msg = "Book Details Added Successfully!";
        }

        Authored atd = at_service.getBooksById(book_id);
        BookCategory bc = bkCat_service.getBookCatbyBookId(book_id);
        model.addAttribute("book", atd);
        model.addAttribute("cat_id", bc.getCat_id());
        model.addAttribute("msg",msg);
        return "editBookPage";
    }
}
