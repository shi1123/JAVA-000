package com.szp.geektime.javaclass.week05.mystarter.pojo;

import java.util.List;

public class MySchool {
    private List<MyClass> myClasses;

    public MySchool(List<MyClass> myClasses) {
        this.myClasses = myClasses;
    }

    @Override
    public String toString() {
        return "MyClass::" + myClasses.toString();
    }
}
