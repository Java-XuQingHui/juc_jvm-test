package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2021/6/3 17:50
 */
public class OutputABC_v1 {

    //通过JDK5中的锁来保证线程的访问的互斥
    private static Lock lock = new ReentrantLock();

    private static int state = 0;

    static class ThreadA extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 0) {
                    System.out.print("A");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 1) {
                    System.out.print("B");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10;) {
                lock.lock();
                if (state % 3 == 2) {
                    System.out.print("C");
                    state++;
                    i++;
                }
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        // 有三个A,B,C线程，A线程输出A，B线程输出B,C线程输出C, 要求同时启动三个线程，按顺序输出ABC,循环10次
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
