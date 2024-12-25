package com.chanpller.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * WebMvcConfigurer mvc配置类可配置的内容,只需要重写WebMvcConfigurer里面的方法即可
 * 1.controller配置
 * 2.全局异常处理器
 * 3. handlerMapping handlerAdapter
 * 4.静态资源处理
 * 5.jsp 视图解析器前后缀
 * 6.json转化器
 * 7.拦截器
 */
@Configuration
@EnableWebMvc//自动配置dispatch拦截处理等
@ComponentScan({"com.chanpller.controller","com.chanpller.exceptionhandler"})
public class MvcConfig implements WebMvcConfigurer {
    //设置默认处理器，也就是可以开启静态资源返回。
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer defaultServletHandlerConfigurer){
        defaultServletHandlerConfigurer.enable();
    }

    //视图解析配置
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("WEB-INF/views/","jsp");
    }

    //添加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor();
    }
}
