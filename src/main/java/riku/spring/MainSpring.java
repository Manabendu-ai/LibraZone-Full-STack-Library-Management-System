package riku.spring;

import com.fasterxml.jackson.core.JsonToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import riku.spring.controllers.*;
import riku.spring.models.*;
import riku.spring.services.AuthoredService;

import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@SpringBootApplication
public class  MainSpring {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MainSpring.class, args);
//
//        BookController bc = context.getBean(BookController.class);
//        AuthorController ac = context.getBean(AuthorController.class);
//        AuthoredController ahc = context.getBean(AuthoredController.class);
//        BookCategoryController bcc = context.getBean(BookCategoryController.class);
//        MemberController mc = context.getBean(MemberController.class);
//        IssueController ic = context.getBean(IssueController.class);
//
//        Books bk = context.getBean(Books.class);
//        bk.setBook_id(105);
//        bk.setTitle("Paradise Ocean");
//        bk.setTotal_copies(3);
//        bk.setAvailable_copies(3);
//
////        bc.addBook(bk);
//
//        Authors at = context.getBean(Authors.class);
//        at.setAuthor_id(904);
//        at.setAuthor_name("Milian Sifer");
//
////        ac.addAuthor(at);
//
//        Authored ah = context.getBean(Authored.class);
//        ah.setAuthor(at);
////        ah.setBook(bk);
////        ahc.addAuthored(ah);
//
//        BookCategory bc1 =context.getBean(BookCategory.class);
//        bc1.setCat_id(9);
//        bc1.setBook(bk);
//
////        bcc.addBookCategory(bc1);
//
//
//        Members mem = context.getBean(Members.class);
//        mem.setId(204);
//        mem.setName("Maya Kumar");
//        mem.setPhone(9908101775L);
//        mem.setEmail("maya@infotech.com");
//        mem.setJoined("2025-01-07");
//
////        mc.addMember(mem);
//
//        Issues is = context.getBean(Issues.class);
//        is.setBook(bk);
//        is.setMember(mem);
//        is.setIssue_date("2025-07-11");
//        is.setDue_date("2025-08-09");
//        is.setReturn_date("2025-08-03");
//
////        ic.addIssue(is);
//
//        Double fine = is.calculateFine("2025-04-09", "2025-04-07");
//        System.out.println(fine);
//
//        List<Books> books = bc.getBooksByGerne("fantasy");
//        System.out.println(books);
//
//        List<Authored> au = ahc.getBooksByAuthor("Crison McCoy");
//        for(Authored a : au){
//            System.out.println(a.getBook().getTitle());
//            System.out.println(a.getAuthor().getAuthor_name());
//        }
//
//        List<Members> members = mc.getMembers();
//        for(Members m : members){
//            System.out.println(m.getName());
//        }
//
//        System.out.println();
//        for (Issues issue : ic.getIssues()) {
//            System.out.println(issue.getMember().getName()+" "+issue.getBook().getTitle());
//        }


//        List<Integer> ins =  null;
//        System.out.println(ins);

//        MemberController mc = context.getBean(MemberController.class);
//        Members mem = mc.getService().getMemberById(125);
//        System.out.println(mem);
    }

}
