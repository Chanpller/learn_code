package com.chanpller.springframework_6.chapter9;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestJavaResourceBundle {

    public static void main(String[] args) {
        System.out.println(ResourceBundle.getBundle("messages",
                new Locale("en","GB")).getString("test"));

        System.out.println(ResourceBundle.getBundle("messages",
                new Locale("zh","CN")).getString("test"));
    }
}
