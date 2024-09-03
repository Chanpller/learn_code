package com.chanpller.springframework_6.chapter3.model;

import java.util.List;
import java.util.Map;

public class Collection {
    private String[] hobi;
    private List<Student> freinds;
    private Map<String,Student> students;

    public String[] getHobi() {
        return hobi;
    }

    public void setHobi(String[] hobi) {
        this.hobi = hobi;
    }

    public List<Student> getFreinds() {
        return freinds;
    }

    public void setFreinds(List<Student> freinds) {
        this.freinds = freinds;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }
}
