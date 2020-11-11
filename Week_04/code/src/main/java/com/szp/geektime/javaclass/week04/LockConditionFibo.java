package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * LockCondition 方式
 */
public class LockConditionFibo {
    private int value;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void calculate(int num) {
        lock.lock();
        try {
            value = Fibo.fibo(num);
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public int getValue() throws InterruptedException {
        lock.lock();
        try {
            while (value == 0) {
                condition.await();
            }
        } finally {
            lock.unlock();
        }
        return value;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        LockConditionFibo lockConditionFibo = new LockConditionFibo();
        Thread thread = new Thread(() -> {
            lockConditionFibo.calculate(36);
        });
        thread.start();
        int result = lockConditionFibo.getValue();

        System.out.println("异步计算结果为：" + result);

        long endTime = System.currentTimeMillis();

        System.out.println("计算时间：" + (endTime - startTime));
    }
}
