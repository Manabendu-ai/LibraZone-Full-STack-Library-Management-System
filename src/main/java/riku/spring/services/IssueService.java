package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.Issues;
import riku.spring.repository.IssueRepo;

import java.util.List;

@Service
@Scope("prototype")
public class IssueService {

    private IssueRepo repo;

    public IssueRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(IssueRepo repo) {
        this.repo = repo;
    }

    public boolean addIssue(Issues issue, int days){
        return repo.save(issue,days);
    }

    public List<Issues> getIssues(){
        return repo.getIssues();
    }

    public Issues getIssueById(int id){
        return repo.getIssueById(id);
    }

    public Issues getIssueByBookId(int id){
        return repo.getIssueByBookId(id);
    }
    public Issues getIssueByMemId(int id){
        return repo.getIssueByMemId(id);
    }

    public List<Issues> getIssueByMemName(String name){
        return repo.getIssueByMemberName(name);
    }
    public List<Issues> getIssueByBookName(String name){
        return repo.getIssueByBookName(name);
    }
}
