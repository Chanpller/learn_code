package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    /**
     *  路径设置为 /product/*
     *    /* 为单层任意字符串  /product/a  /product/aaa 可以访问此handler
     *    /product/a/a 不可以
     *  路径设置为 /product/
     *   / 为任意层任意字符串  /product/a  /product/aaa 可以访问此handler
     *   /product/a/a 也可以访问
     */
    @RequestMapping("/product/*")
    @ResponseBody
    public String show(){
        System.out.println("ProductController.show");
        return "product show!";
    }
}