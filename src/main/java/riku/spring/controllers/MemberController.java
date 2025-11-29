package riku.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import riku.spring.models.Members;
import riku.spring.services.MemberService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@Scope("prototype")
public class MemberController {

    private MemberService service;

    public MemberService getService() {
        return service;
    }

    @Autowired
    public void setService(MemberService service) {
        this.service = service;
    }

    public void addMember(Members member){
        if(service.addMember(member)){
            System.out.println(member+ " Added Successfully!");
            return;
        }
        System.out.println("Member Couldn't be added!");
    }

    public List<Members> getMembers(){
        return service.getMembers();
    }

    @RequestMapping("/members")
    public String members(Model model){
        List<Members> mems = getMembers();
        model.addAttribute("mems", mems);
        return "membersPage";
    }

    @RequestMapping("/searchMember")
    public String searchMember(
            @RequestParam("search_member") String para,
            Model model
    ){
        if(para.isEmpty()){
            List<Members> mems = service.getMembers();
            model.addAttribute("mems", mems);
            model.addAttribute("para", para);
            return "membersPage";
        }
        Members mem = service.getMemberByName(para);
        if (mem == null) {
            try{
                int id = Integer.parseInt(para);
                mem = service.getMemberById(id);
            } catch (Exception e){
                mem = null;
            }
        }
        List<Members> mems = null;
        if(mem != null){
            mems = Collections.singletonList(mem);
        }
        model.addAttribute("mems", mems);
        model.addAttribute("para", para);
        return "membersPage";
    }

    @RequestMapping("/addmem")
    public String addMember(){
        return "addmem";
    }


    @RequestMapping("/editmem")
    public String editMember(
        @RequestParam("mem_id") int mem_id,
        Model model
    ){
        Members mem = service.getMemberById(mem_id);
        model.addAttribute("mem", mem);
        return "editmem";
    }

    @RequestMapping("/update_mem_to_db")
    public String updateMember(
            @ModelAttribute Members mem,
            Model model
    ){
        boolean m1 = service.updateMemberById(mem);
        String msg = "";
        if (m1){
            msg = "Book Details Added Successfully!";
        }
        model.addAttribute("mem", mem);
        model.addAttribute("msg", msg);
        return "editmem";
    }
    @RequestMapping("/add_mem_to_db")
    public String addMember(
            @ModelAttribute Members mem,
            Model model
    ){
        boolean m1 = service.addMember(mem);
        String msg="";
        if(m1){
            msg = "Member Details Added Successfully!";
        }
        model.addAttribute("msg", msg);
        return "addmem";
    }
}
