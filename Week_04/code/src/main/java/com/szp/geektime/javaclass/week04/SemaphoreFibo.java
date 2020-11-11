package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;
import java.util.concurrent.Semaphore;
/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * Semaphore 方式
 */
public class SemaphoreFibo {
    private volatile int value;
    private Semaphore semaphore;

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void caculate(int num) throws InterruptedException {
        semaphore.acquire();
        value = Fibo.fibo(num);
        semaphore.release();
    }

    public int getValue() throws InterruptedException {
        int result;
        semaphore.acquire();
        result = value;
        semaphore.release();
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        SemaphoreFibo semaphoreFibo = new SemaphoreFibo();
        semaphoreFibo.setSemaphore(new Semaphore(1));
        Thread thread = new Thread(()-> {
            try {
                semaphoreFibo.caculate(36);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        //确保计算线程先启动
        Thread.sleep(10);

        int result = semaphoreFibo.getValue();

        System.out.println("异步计算结果为：" + result);

        long endTime = System.currentTimeMillis();

        System.out.println("计算时间：" + (endTime - startTime));
    }

}
