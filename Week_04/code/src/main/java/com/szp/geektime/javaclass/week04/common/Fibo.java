package com.szp.geektime.javaclass.week04.common;

public class Fibo {
    public static int fibo(int a){
        if(a== 0 || a == 1)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
