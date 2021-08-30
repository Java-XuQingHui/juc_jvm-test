package com.atguigu.juc;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import	java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>扣减库存测试类</p>
 *
 * @author xuqinghui
 * @create 2020/10/20 11:56
 */
public class InventoryDecTest {

	//定义库存数
	private static Integer inventory = 1001;
	//定义线程数
	private static final int NUM = 1000;
	//定义阻塞队列
	private static LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue();

	static ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(inventory, inventory,
				10L, TimeUnit.SECONDS, blockingQueue);
		CountDownLatch downLatch = new CountDownLatch(NUM);
		long start = System.currentTimeMillis();
		for (int i = 0; i < NUM; i++) {
			poolExecutor.execute(new Runnable() {
				@Override
				public void run() {
					/*******/
					lock.lock();
					inventory--;
					lock.unlock();
					/*******/
					System.out.println("剩余库存数：" + inventory);
					downLatch.countDown();
				}
			});
		}
		poolExecutor.shutdown();
		try {
			downLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("执行线程数：" + NUM + "--总的耗时：" + (end - start) + "---库存数：" + inventory) ;
	}


}
