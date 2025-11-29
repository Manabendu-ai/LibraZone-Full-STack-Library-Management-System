package riku.spring.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Authored {

    private Books book;
    private Authors author;

    public Books getBook() {
        return book;
    }

    @Autowired
    public void setBook(Books book) {
        this.book = book;
    }

    public Authors getAuthor() {
        return author;
    }

    @Autowired
    public void setAuthor(Authors author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return "Author ID : "+getAuthor().getAuthor_id()+
                "\nBook ID :"+getBook().getBook_id()+"\n";
    }
}
