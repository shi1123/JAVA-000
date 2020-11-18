package com.szp.geektime.javaclass.week05.myspring;

import com.szp.geektime.javaclass.week05.myspring.factory.BeanFactory;
import com.szp.geektime.javaclass.week05.myspring.pojo.Student;
import com.szp.geektime.javaclass.week05.myspring.pojo.Teacher;

public class MySpringTest {
    public static void main(String[] args) {
        Teacher teacher = (Teacher) BeanFactory.getBean("teacher");
        System.out.println(teacher);

        Student student = (Student) BeanFactory.getBean("student");
        System.out.println(student);
    }
}
