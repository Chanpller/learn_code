package config;

import interceptor.Process01Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

//TODO: SpringMVC对应组件的配置类 [声明SpringMVC需要的组件信息]

//TODO: 导入handlerMapping和handlerAdapter的三种方式
//1.自动导入handlerMapping和handlerAdapter [推荐]
//2.可以不添加,springmvc会检查是否配置handlerMapping和handlerAdapter,没有配置默认加载
//3.使用@Bean方式配置handlerMapper和handlerAdapter
@EnableWebMvc  //json数据处理,必须使用此注解,因为他会加入json处理器，handlerAdapter配置了json转换器，以及RequestMappingHandlerMapping和RequestMappingHandlerAdapter。所以步涌再自己导入
@Configuration
@ComponentScan(basePackages = "com.chanpller.spring6_springboot3_ssm.chapter4.controller") //TODO: 进行controller扫
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
    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将拦截器添加到Springmvc环境,默认拦截所有Springmvc分发的请求
        registry.addInterceptor(new Process01Interceptor());

        //精准匹配,设置拦截器处理指定请求 路径可以设置一个或者多个,为项目下路径即可
        //addPathPatterns("/common/request/one") 添加拦截路径
        //也支持 /* 和 / 模糊路径。 * 任意一层字符串  任意层 任意字符串
//        registry.addInterceptor(new Process01Interceptor()).addPathPatterns("/common/request/one","/common/request/tow");

        //排除匹配,排除应该在匹配的范围内排除
        //addPathPatterns("/common/request/one") 添加拦截路径
        //excludePathPatterns("/common/request/tow"); 排除路径,排除应该在拦截的范围内
//        registry.addInterceptor(new Process01Interceptor())
//                .addPathPatterns("/common/request/one","/common/request/tow")
//                .excludePathPatterns("/common/request/tow");
    }
}
