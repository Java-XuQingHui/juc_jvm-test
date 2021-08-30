package com.atguigu.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>简单描述该类的作用</p>
 *
 * @author xuqinghui
 * @create 2020/4/24 15:16
 */
public class ABAProblemTest {

	public static AtomicInteger a = new AtomicInteger(1);

	public static void main(String[] args){

		Thread main = new Thread(() -> {
			System.out.println("操作线程" + Thread.currentThread() +",初始值 = " + a);  //定义变量 a = 1
			try {
				Thread.sleep(1000);  //等待1秒 ，以便让干扰线程执行
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean isCASSuccess = a.compareAndSet(1,2); // CAS操作
			System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
		},"主操作线程");

		Thread other = new Thread(() -> {
			Thread.yield();  //确保thread-main线程优先执行
			a.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
			System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ a);
			a.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
			System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ a);
		},"干扰线程");

		main.start();
		other.start();
	}

}
