package com.szp.geektime.javaclass.week05.mystarter.pojo;

import java.util.ArrayList;
import java.util.List;

public class MyClass {
    private int id;
    private String name;

    public MyClass(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private List<MyStudent> students = new ArrayList<>();

    public void addStudent(MyStudent student) {
        students.add(student);
    }

    @Override
    public String toString() {
        return "MyClass::" + students.toString();
    }
}
