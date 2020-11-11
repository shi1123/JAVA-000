package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * ThreadJoin 方式
 */
public class ThreadJoinFibo {
    private static volatile int value;

    public static void calculate(){
        value = Fibo.fibo(36);
    }

    public static int getValue(){
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Thread thread = new Thread(()->{
            SynchronizedFibo.calculate();
        });
        thread.start();

        thread.join();
        int result = SynchronizedFibo.getValue();

        System.out.println("异步计算结果为：" + result);

        long endTime = System.currentTimeMillis();

        System.out.println("计算时间：" + (endTime - startTime));
    }
}
