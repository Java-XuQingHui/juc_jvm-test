package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>模拟并发示例</p>
 *
 * @author xuqinghui
 * @create 2020/4/7 13:40
 */
public class Parallellimit {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newCachedThreadPool();
		CountDownLatch cdl = new CountDownLatch(100);
		for (int i = 0; i < 100; i++) {
			CountRunnable runnable = new CountRunnable(cdl);
			pool.execute(runnable);
		}
	}

}

class CountRunnable implements Runnable {
	private CountDownLatch countDownLatch;
	public CountRunnable(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
	@Override
	public void run() {
		try {
			synchronized (countDownLatch) {
				/*** 每次减少一个容量*/
				countDownLatch.countDown();
				System.out.println("thread counts = " + (countDownLatch.getCount()));
			}
			countDownLatch.await();
			System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


/**
 *   1.countDownLatch类中只提供了一个构造器：
 *   //参数count为计数值
     public CountDownLatch(int count) {  };

	 2.类中有三个方法是最重要的：
	 //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
	 public void await() throws InterruptedException { };
	 //和await()类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
	 public boolean await(long timeout, TimeUnit unit) throws InterruptedException { };
	 //将count值减1
	 public void countDown() { };
 */