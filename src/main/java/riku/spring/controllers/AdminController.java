package riku.spring.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import riku.spring.models.Admin;
import riku.spring.services.AdminService;

@Controller
public class AdminController {

    private AdminService service;

    public AdminService getService() {
        return service;
    }

    @Autowired
    public void setService(AdminService service) {
        this.service = service;
    }

    @RequestMapping("/login")
    public String login(){
        return "loginPage";
    }
    @RequestMapping("/home")
    public String home(HttpSession session){
        if(session.getAttribute("admin") == null){
            return "redirect:/login";
        }
        return "home";
    }

    @RequestMapping("/about")
    public String about(HttpSession session){
        if(session.getAttribute("admin") == null){
            return "redirect:/login";
        }
        return "aboutus";
    }

    @RequestMapping("/register")
    public String register(){
        return "registerPage";
    }

    @RequestMapping("/reg")
    public String addAdmin(@ModelAttribute Admin admin, Model model){
        if(service.addAdmin(admin)){
            model.addAttribute("admin", admin);
            return "SuccessRegister";
        }
        return "registerPage";
    }

    @RequestMapping("/contact")
    public String contact(HttpSession session){
        if(session.getAttribute("admin")==null){
            return "redirect:/login";
        }
        return "contacts";
    }

    @RequestMapping("/auth")
    public String auth(@ModelAttribute Admin admin, Model model, HttpSession session){
            System.out.println(admin.getId());
            System.out.println(admin.getName());
            admin = service.authAdmin(admin);
            if (admin != null) {
                session.setAttribute("admin", admin);
                model.addAttribute("admin", admin);
                return "home";
            }
            return "AdminNotFound";

    }

}
