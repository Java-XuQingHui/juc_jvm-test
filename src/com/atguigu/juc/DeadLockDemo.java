package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

/**
 * <p>死锁示例</p>
 *
 * @author xuqinghui
 * @create 2021/9/4 10:25
 */

class HoldLockThread implements Runnable {

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {

        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有： " + lockA + "\t 尝试获取：" + lockB);
            // 模拟暂停一下
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有： " + lockB + "\t 尝试获取：" + lockA);
            }

        }

    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();

    }

}
