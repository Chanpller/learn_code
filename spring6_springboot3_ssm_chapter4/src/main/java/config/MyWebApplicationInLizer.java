package config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;

public class MyWebApplicationInLizer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("程序启动加载");
    }
}
