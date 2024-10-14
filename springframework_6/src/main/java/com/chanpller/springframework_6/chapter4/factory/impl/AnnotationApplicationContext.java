package com.chanpller.springframework_6.chapter4.factory.impl;

import com.chanpller.springframework_6.chapter4.annotion.Bean;
import com.chanpller.springframework_6.chapter4.annotion.Di;
import com.chanpller.springframework_6.chapter4.factory.ApplicationContext;
import com.chanpller.springframework_6.chapter4.service.UserService;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
                loadBean(new File(filePath));
            }
            loadFild();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void loadBean(File sourceFile){
       if(sourceFile.isDirectory()){
           File[] files = sourceFile.listFiles();
           for (File file : files) {
               loadBean(file);
           }
       }else{
           String absolutePath = sourceFile.getAbsolutePath();
           String substring = absolutePath.substring(rootPath.length()-1);
           String beanPackge = substring.replace("\\", ".");
           if(beanPackge.endsWith(".class")){
               beanPackge = beanPackge.substring(0,beanPackge.length()-6);
               System.out.println(beanPackge);
               try {
                   Class aClass = Class.forName(beanPackge);
                   if(!aClass.isInterface()){
                       Annotation annotation = aClass.getAnnotation(Bean.class);
                       if(annotation!=null){
                           Constructor declaredConstructor = aClass.getDeclaredConstructor();
                           declaredConstructor.setAccessible(true);
                           Object bean = declaredConstructor.newInstance();
                           Class[] interfaces = aClass.getInterfaces();
                           if(interfaces == null || interfaces.length == 0){
                               beanFactory.put(aClass,bean);
                           }else{
                               beanFactory.put(interfaces[0],bean);
                           }
                       }

                   }
               } catch (ClassNotFoundException e) {
                   e.printStackTrace();
               } catch (NoSuchMethodException e) {
                   e.printStackTrace();
               } catch (InvocationTargetException e) {
                   e.printStackTrace();
               } catch (InstantiationException e) {
                   e.printStackTrace();
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
       }
    }
    public void loadFild(){
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for(Map.Entry<Class, Object> entry: entries){
            Class aClass = entry.getValue().getClass();
            Field[] declaredFields = aClass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                Di annotation = declaredField.getAnnotation(Di.class);
                if(annotation !=null ){
                    declaredField.setAccessible(true);
                    try {
                        Class type = declaredField.getType();
                        declaredField.set(entry.getValue(),beanFactory.get(type));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) {
        AnnotationApplicationContext annotationApplicationContext = new AnnotationApplicationContext("com.chanpller.springframework_6.chapter4");
        UserService bean = (UserService)annotationApplicationContext.getBean(UserService.class);
        bean.save();
    }
}
