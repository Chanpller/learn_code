package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//TODO: SpringMVC对应组件的配置类 [声明SpringMVC需要的组件信息]

//TODO: 导入handlerMapping和handlerAdapter的三种方式
//1.自动导入handlerMapping和handlerAdapter [推荐]
//2.可以不添加,springmvc会检查是否配置handlerMapping和handlerAdapter,没有配置默认加载
//3.使用@Bean方式配置handlerMapper和handlerAdapter
@EnableWebMvc  //json数据处理,必须使用此注解,因为他会加入json处理器，handlerAdapter配置了json转换器，以及RequestMappingHandlerMapping和RequestMappingHandlerAdapter。所以步涌再自己导入
@Configuration
@ComponentScan(basePackages = "controller") //TODO: 进行controller扫
//WebMvcConfigurer springMvc进行组件配置的规范,配置组件,提供各种方法! 前期可以实现
public class SpringMvcConfig implements WebMvcConfigurer {
    /*
    //当没有EnableWebMvc注解时，才需要要下面两个bean注入。
    @Bean
    public HandlerMapping handlerMapping(){
        return new RequestMappingHandlerMapping();
    }

    @Bean
    public HandlerAdapter handlerAdapter(){
        return new RequestMappingHandlerAdapter();
    }*/
    //配置jsp对应的视图解析器
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //快速配置jsp模板语言对应的
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    //开启静态资源处理 <mvc:default-servlet-handler/>
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
