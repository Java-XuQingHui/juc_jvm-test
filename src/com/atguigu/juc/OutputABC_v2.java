package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2021/6/4 17:57
 */
public class OutputABC_v2 {

    private static Lock lock = new ReentrantLock();

    private static int count = 0;

    private static Condition A = lock.newCondition();
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();


    static class ThreadA extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0)
                        A.await(); // A阻塞

                    System.out.print("A");
                    count++;
                    B.signal(); // A执行完之后唤醒B
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

    }

    static class ThreadB extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1)
                        B.await(); // B阻塞

                    System.out.print("B");
                    count++;
                    C.signal(); // B执行完之后唤醒C
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }

    }

    static class ThreadC extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2)
                        C.await(); // C阻塞

                    System.out.print("C");
                    count++;
                    A.signal(); // C执行完后唤醒A

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }


        }

    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }


}
