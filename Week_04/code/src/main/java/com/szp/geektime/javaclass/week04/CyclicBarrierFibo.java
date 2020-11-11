package com.szp.geektime.javaclass.week04;

import com.szp.geektime.javaclass.week04.common.Fibo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * CyclicBarrier 方式
 */
public class CyclicBarrierFibo {
    private volatile int value;
    private CyclicBarrier cyclicBarrier;

    public void setCyclicBarrier(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    public void calculate(int num) throws BrokenBarrierException, InterruptedException {
        value = Fibo.fibo(num);
        cyclicBarrier.await();
    }

    public int getValue() {
        return value;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        CyclicBarrierFibo cyclicBarrierFibo = new CyclicBarrierFibo();
        cyclicBarrierFibo.setCyclicBarrier(new CyclicBarrier(1,()->{
            int result = cyclicBarrierFibo.getValue();

            System.out.println("异步计算结果为：" + result);

            long endTime = System.currentTimeMillis();

            System.out.println("计算时间：" + (endTime - startTime));
        }));
        Thread thread = new Thread(() -> {
            try {
                cyclicBarrierFibo.calculate(36);
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }


}
