package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;

/**
 * <p>模拟并发竞争</p>
 *
 * @author xuqinghui
 * @create 2020/4/7 13:24
 */
public class ConcurrencyDemo implements Runnable{
	// 定义一个库存
	static int inventory = 1;
	// 用到了一个CountDownLatch发令枪，等10个线程都就绪了一起去扣减库存
	private static final int NUM = 10;
	private static CountDownLatch cdl = new CountDownLatch(NUM);

	public static void main(String[] args) {
		for (int i = 1; i <= NUM ; i++) {
			new Thread(new ConcurrencyDemo()).start();
			cdl.countDown();
		}
	}

	@Override
	public void run() {
		try {
			cdl.await();
			if (inventory > 0) {
				Thread.sleep(5);
				inventory--;
			}
			System.out.println(inventory);
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