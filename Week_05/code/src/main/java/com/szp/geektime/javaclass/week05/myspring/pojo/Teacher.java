package com.szp.geektime.javaclass.week05.myspring.pojo;

public class Teacher {
    private String name;
    private Student student;

    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", student=" + student +
                '}';
    }
}
