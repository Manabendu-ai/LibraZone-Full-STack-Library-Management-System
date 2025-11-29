package riku.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import riku.spring.models.Books;
import riku.spring.models.Fine;
import riku.spring.models.Issues;
import riku.spring.models.Members;
import riku.spring.services.BookService;
import riku.spring.services.IssueService;
import riku.spring.services.MemberService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class IssueController {

    private IssueService service;

    private BookService book_service;
    private MemberService mem_service;
    private Fine fine;

    public BookService getBook_service() {
        return book_service;
    }

    @Autowired
    public void setBook_service(BookService book_service) {
        this.book_service = book_service;
    }

    public MemberService getMem_service() {
        return mem_service;
    }

    @Autowired
    public void setMem_service(MemberService mem_service) {
        this.mem_service = mem_service;
    }

    public IssueService getService() {
        return service;
    }

    @Autowired
    public void setService(IssueService service) {
        this.service = service;
    }

//    public void addIssue(Issues issue){
//        if(service.addIssue(issue)){
//            System.out.println(issue+ " Added Into to Database!");
//            return;
//        }
//        System.out.println("Issue Could not be added!");
//    }

    public List<Issues> getIssues(){
        return service.getIssues();
    }

    @RequestMapping("/trackrecords")
    public String trackRecords(
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("admin") == null){
            return "loginPage";
        }
        List<Issues> tracks = getIssues();
        model.addAttribute("tracks",tracks);
        return "issuePage";
    }

    @RequestMapping("/newdistro")
    public String newDistro(HttpSession session){
        if(session.getAttribute("admin") == null){
            return "loginPage";
        }
        return "newdistropage";
    }

    @RequestMapping("/cal_fine")
    public String CalculateFine(
            @RequestParam("id") int id,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("admin") == null){
            return "loginPage";
        }
        List<Issues> tracks = getIssues();
        model.addAttribute("tracks",tracks);
        return "issuePage";
    }


    @RequestMapping("/saveIssue")
    public String saveIssue(
            @RequestParam("book_id") int book_id,
            @RequestParam("mem_id") int mem_id,
            @RequestParam("days") int days,
            HttpSession session,
            Model model
    ){
        if(session.getAttribute("admin") == null){
            return "loginPage";
        }

        Books book = book_service.getBooksById(book_id);
        Members mem = mem_service.getMemberById(mem_id);

        String msg = "";
        if(mem != null && book != null){
            Issues issue = new Issues();
            issue.setBook(book);
            issue.setMember(mem);
            boolean i = service.addIssue(issue, days);
            if(i){
                msg="Record Added Sucessfully";
            }
        }

        model.addAttribute("msg",msg);
        return "newdistropage";
    }

    @RequestMapping("search_issue")
    public String searchIssues(
            @RequestParam("val") String param,
            Model model
    ){
        List<Issues> issues = new ArrayList<>();
        try{
            int id = Integer.parseInt(param);

            Issues issue = service.getIssueByBookId(id);
            if(issue!=null){
                issues.add(issue);
            }

            if(issues.isEmpty()){
                issue = service.getIssueByMemId(id);
                if(issue!=null){
                    issues.add(issue);
                }
            }

            if(issues.isEmpty()){
                issue = service.getIssueById(id);
                if(issue!=null){
                    issues.add(issue);
                }
            }
        } catch(Exception e){
            issues = service.getIssueByBookName(param);
            if(issues==null || issues.isEmpty()){
                issues = service.getIssueByMemName(param);
            }
        }

        if(issues.isEmpty()){
            issues=null;
        }

        model.addAttribute("tracks", issues);
        return "issuePage";
    }

    @Autowired
    public void setFine(Fine fine) {
        this.fine = fine;
    }
}
