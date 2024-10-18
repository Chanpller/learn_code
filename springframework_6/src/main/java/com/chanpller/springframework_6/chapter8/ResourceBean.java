package com.chanpller.springframework_6.chapter8;

import org.springframework.core.io.Resource;

public class ResourceBean {

    private Resource res;
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setRes(Resource res) {
        this.res = res;
    }
    public Resource getRes() {
        return res;
    }

    public void parse(){
        System.out.println(res.getFilename());
        System.out.println(res.getDescription());
    }
}