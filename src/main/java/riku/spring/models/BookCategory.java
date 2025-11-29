package riku.spring.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BookCategory {

    private int cat_id;
    private Books book;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public Books getBook() {
        return book;
    }

    @Autowired
    public void setBook(Books book) {
        this.book = book;
    }

}
