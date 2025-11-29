package riku.spring.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class HelloServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res){
        try {
            res.setContentType("text/html");
            res.getWriter().write("<h1>Hello From Servlet</h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
