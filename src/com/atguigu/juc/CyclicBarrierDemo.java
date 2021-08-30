package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <p>正序计数器</p>
 *
 * @author xuqinghui
 * @create 2020/4/24 10:00
 */
public class CyclicBarrierDemo {

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
			System.out.println("---------召唤神龙------------");
		});

		for (int i = 1; i <= 7 ; i++) {
			final int tempInt = i;
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName() + "\t收集到" + tempInt + "龙珠");
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			},String.valueOf(i)).start();
		}
	}
}

/**
 * CyclicBarrier的字面意思是可循环（Cyclic）使用的屏障（Barrier）。
   它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
   直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。
   线程进入屏障通过CyclicBarrier的await()方法。
*/