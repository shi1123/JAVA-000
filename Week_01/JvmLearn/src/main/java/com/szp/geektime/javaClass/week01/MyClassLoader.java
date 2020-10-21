package com.szp.geektime.javaClass.week01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
//        return super.findClass(name);
        File file = new File(this.getClass().getResource("/Hello.xlass").getPath());
        int fileLen = (int) file.length();
        byte[] fileContent = new byte[fileLen];

        try {
            FileInputStream fis = new FileInputStream(file);
            fis.read(fileContent);
            for (int i = 0; i < fileContent.length; i++) {
                fileContent[i] = (byte)(255- fileContent[i]);
            }
            return defineClass(name, fileContent, 0, fileLen);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = new MyClassLoader().findClass("Hello");
            Object hello = clazz.newInstance();
            Method method = clazz.getMethod("hello");
            method.invoke(hello);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
