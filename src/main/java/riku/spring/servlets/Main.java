package riku.spring.servlets;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Tomcat tom = new Tomcat();
        tom.setPort(8080);
        tom.getConnector();

        String docBase = new File(".").getAbsolutePath();
        Context context = tom.addContext("",docBase);
        Tomcat.addServlet(context, "HelloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello","HelloServlet");

        try {
            tom.start();
            tom.getServer().await();
        } catch (LifecycleException e) {
            throw new RuntimeException(e);
        }
    }
}
