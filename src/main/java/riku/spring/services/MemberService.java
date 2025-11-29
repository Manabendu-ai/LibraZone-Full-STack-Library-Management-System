package riku.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import riku.spring.models.Members;
import riku.spring.repository.MemberRepo;

import javax.sound.midi.MetaMessage;
import java.lang.reflect.Member;
import java.util.List;

@Service
@Scope("prototype")
public class MemberService {
    private MemberRepo repo;

    public MemberRepo getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(MemberRepo repo) {
        this.repo = repo;
    }

    public boolean addMember(Members member){
        return repo.save(member);
    }

    public List<Members> getMembers(){
        return repo.getMembers();
    }

    public Members getMemberById(int id){
        return repo.getMembersById(id);
    }

    public Members getMemberByName(String name){
        return repo.getMemberByName(name);
    }

    public boolean updateMemberById(Members mem) {
        return repo. updateMemberById(mem);
    }
}
