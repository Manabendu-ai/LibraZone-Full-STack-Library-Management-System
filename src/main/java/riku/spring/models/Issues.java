package riku.spring.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Component
@Scope("prototype")
public class Issues implements Fine{

    private int id;
    private Books book;
    private Members member;
    private String issue_date;
    private String due_date;
    private String return_date;
    @Value("0.0")
    private Double fine;

    public Books getBook() {
        return book;
    }

    @Autowired
    public void setBook(Books book) {
        this.book = book;
    }

    public Members getMember() {
        return member;
    }

    @Autowired
    public void setMember(Members member) {
        this.member = member;
    }

    public String getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(String issue_date) {
        this.issue_date = issue_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        if(return_date == null){
            this.return_date = "pending..";
            return;
        }
        this.return_date = return_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public Double getFine() {
        return fine;
    }
    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "Issues{" +
                "book_id= " + book.getBook_id() +
                ", member_id= " + member.getMem_id() +
                ", issue_date='" + issue_date + '\'' +
                ", due_date='" + due_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", fine=" + fine +
                '}';
    }

    @Override
    public Double calculateFine(String due_date, String return_date) {
        if(return_date == null || return_date.toLowerCase().contains("pending")){
            return 0.0;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate due = LocalDate.parse(due_date,formatter);
        LocalDate ret = LocalDate.parse(return_date,formatter);
        long daysBetween = ChronoUnit.DAYS.between(due, ret);

        if(daysBetween<=0){
            return 0.0;
        }

        int ifine = 50;
        double amount=0;
        int j=1;
        for(int i = 1; i<=daysBetween; i++){
            amount += ifine;
            if(j%7==0){
                ifine += 50;
            }
            j++;
        }
        return amount;
    }


}
