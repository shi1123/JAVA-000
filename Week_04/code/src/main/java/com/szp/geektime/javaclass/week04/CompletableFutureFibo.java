package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;

import java.util.concurrent.CompletableFuture;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * CompletableFuture 方式
 */
public class CompletableFutureFibo {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int result = CompletableFuture.supplyAsync(()->calculate()).join();

        System.out.println("异步计算结果为：" + result);

        long endTime = System.currentTimeMillis();

        System.out.println("计算时间：" + (endTime - startTime));
    }

    private static int calculate(){
        return Fibo.fibo(36);
    }

}
