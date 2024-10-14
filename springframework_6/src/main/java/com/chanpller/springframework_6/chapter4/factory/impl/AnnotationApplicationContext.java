package com.chanpller.springframework_6.chapter4.factory.impl;

import com.chanpller.springframework_6.chapter4.factory.ApplicationContext;

import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {
    private Map<Class,Object> beanFactory = new HashMap<>();
    private String rootPath;
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    public AnnotationApplicationContext(String basePackge) {
        try {
            String basePackgeDir = basePackge.replace(".", "\\");
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(basePackgeDir);
            while(resources.hasMoreElements()){
                URL url = resources.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                rootPath = filePath.substring(0,filePath.length()-basePackgeDir.length());
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        AnnotationApplicationContext annotationApplicationContext = new AnnotationApplicationContext("com.chanpller.springframework_6.chapter4.factory");
    }
}
