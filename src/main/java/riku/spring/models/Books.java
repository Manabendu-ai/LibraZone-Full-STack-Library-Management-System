package riku.spring.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class Books {
    private int book_id;
    private String title;
    private int total_copies;
    private int available_copies;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotal_copies() {
        return total_copies;
    }

    public void setTotal_copies(int total_copies) {
        this.total_copies = total_copies;
    }

    public int getAvailable_copies() {
        return available_copies;
    }

    public void setAvailable_copies(int available_copies) {
        this.available_copies = available_copies;
    }

    @Override
    public String toString() {
        return "BookController{" +
                "book_id=" + book_id +
                ", title='" + title + '\'' +
                ", total_copies=" + total_copies +
                ", available_copies=" + available_copies +
                '}';
    }

}
