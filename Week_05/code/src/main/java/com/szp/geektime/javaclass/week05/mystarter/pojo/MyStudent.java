package com.szp.geektime.javaclass.week05.mystarter.pojo;

import java.util.HashMap;
import java.util.Map;

public class MyStudent {
    private int id;
    private String name;

    public MyStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", id);
        map.put("name", name);
        return "Student::" + map.toString();
    }
}
