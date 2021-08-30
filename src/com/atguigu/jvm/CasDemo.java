package com.atguigu.jvm;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * <p>CAS问题</p>
 *
 * @author: xuqinghui
 * @create: 2020/4/26 17:33
 * @param
 * @return {@link }
 **/

public class CasDemo extends Thread{

	public static AtomicInteger race = new AtomicInteger(0);

	private static final int THREADS_COUNT = 20;

	private static CountDownLatch countDownLatch = new CountDownLatch(THREADS_COUNT);

	// 不嫩保证原子可见性 解决方法：1.synchronized 2.使用并发原子操作类
//	public synchronized static void increase() {
//		race++;
//	}

	public static void increase() {
		// 非原子操作 取值 +1 写值
		// race++;
		race.getAndIncrement();
	}

	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[THREADS_COUNT];
		for (int i = 0; i < THREADS_COUNT; i++) {
			threads[i] = new Thread(new Runnable() {
				@Override
				public void run() {
					for (int i = 0; i < 10000; i++) {
						increase();
					}
					countDownLatch.countDown();
				}
			});
			threads[i].start();
		}
		countDownLatch.await();
		System.out.println(race);
	}
}
