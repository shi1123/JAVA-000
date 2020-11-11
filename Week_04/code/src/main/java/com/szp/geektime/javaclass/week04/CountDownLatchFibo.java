package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;

import java.util.concurrent.CountDownLatch;
/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * CountDownLatch 方式
 */
public class CountDownLatchFibo {
    private volatile int value;
    private CountDownLatch countDownLatch;

    public CountDownLatchFibo(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    public void calculate(int num){
        value = Fibo.fibo(num);
        countDownLatch.countDown();
    }

    public int getValue() throws InterruptedException {
        countDownLatch.await();
        return value;
    }
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        CountDownLatchFibo countDownLatchFibo = new CountDownLatchFibo(new CountDownLatch(1));
        Thread thread = new Thread(()-> {
            countDownLatchFibo.calculate(36);
        });
        thread.start();

        int result = countDownLatchFibo.getValue();

        System.out.println("异步计算结果为：" + result);

        long endTime = System.currentTimeMillis();

        System.out.println("计算时间：" + (endTime - startTime));
    }
}
