package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

/**
 * <p>倒序计数器</p>
 *
 * @author xuqinghui
 * @create 2020/4/23 14:19
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {

		CountDownLatch countDownLatch = new CountDownLatch(6);

		for (int i = 1; i <= 6 ; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t离开教室");
				countDownLatch.countDown();
			}, String.valueOf(i)).start();
		}

		countDownLatch.await();
		System.out.println(Thread.currentThread().getName() + "\t教室关门");
	}

}

/**
 * * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 *   其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 *   当计数器的值变为0时，此时await方法阻塞的线程会被唤醒，继续执行。
 */