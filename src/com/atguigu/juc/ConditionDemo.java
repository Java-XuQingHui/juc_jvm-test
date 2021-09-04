package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *     多线程之间按顺序调用，实现A->B->C
 * 		三个线程启动，要求如下：
 * 		A打印5次， B打印10次 C打印15次 轮询10轮
 * </p>
 *
 * @author xuqinghui
 * @create 2020/4/20 12:57
 */

class ShareData {
	// A-1 B-2 C-3
	private int flag = 1;

	private Lock lock = new ReentrantLock();

	Condition c1  = lock.newCondition();
	Condition c2  = lock.newCondition();
	Condition c3  = lock.newCondition();

	public void print5() {
		lock.lock();

		try {
			// 1.判断
			while(flag != 1) {
				c1.await();
			}
			// 2.操作
			for (int i = 1; i <= 5 ; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			// 3.通知B
			flag = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print10() {
		lock.lock();

		try {
			// 1.判断
			while(flag != 2) {
				c2.await();
			}
			// 2.操作
			for (int i = 1; i <= 10 ; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			// 3.通知C
			flag = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print15() {
		lock.lock();

		try {
			// 1.判断
			while(flag != 3) {
				c3.await();
			}
			// 2.操作
			for (int i = 1; i <= 15 ; i++) {
				System.out.println(Thread.currentThread().getName() + "\t" + i);
			}
			// 3.通知A
			flag = 1;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}


}

public class ConditionDemo {

	public static void main(String[] args) {
		ShareData shareData = new ShareData();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				shareData.print5();
			}
		},"A").start();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				shareData.print10();
			}
		},"B").start();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				shareData.print15();
			}
		},"C").start();
	}

}
