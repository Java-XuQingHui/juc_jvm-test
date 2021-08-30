package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>jdk1.5 新特性之互斥锁</p>
 *
 * @author xuqinghui
 * @create 2020/4/13 21:09
 */
public class ReentrantLockDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Printer p = new Printer();

		new Thread() {
			public void run() {
				while (true) {
					try {
						p.print1();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					try {
						p.print2();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					try {
						p.print3();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

}

class Printer {
	private ReentrantLock r = new ReentrantLock();
	private Condition c1 = r.newCondition();
	private Condition c2 = r.newCondition();
	private Condition c3 = r.newCondition();

	private int flag = 1;

	public void print1() throws InterruptedException {
		r.lock(); // 获取锁
		if (flag != 1) {
			c1.await();
		}
		System.out.print("黑");
		System.out.print("马");
		System.out.print("程");
		System.out.print("序");
		System.out.print("员");
		System.out.print("\r\n");
		flag = 2;
		c2.signal();
		r.unlock(); // 释放锁
	}

	public void print2() throws InterruptedException {
		r.lock();
		if (flag != 2) {
			c2.await();
		}
		System.out.print("传");
		System.out.print("智");
		System.out.print("播");
		System.out.print("客");
		System.out.print("\r\n");
		flag = 3;
		c3.signal();
		r.unlock();
	}

	public void print3() throws InterruptedException {
		r.lock();
		if (flag != 3) {
			c3.await();
		}
		System.out.print("i");
		System.out.print("t");
		System.out.print("h");
		System.out.print("e");
		System.out.print("i");
		System.out.print("m");
		System.out.print("a");
		System.out.print("\r\n");
		flag = 1;
		c1.signal();
		r.unlock();
	}

}


/**
 * JDK1.5的新特性互斥锁
	 1.同步
     使用ReentrantLock类的lock()和unlock()方法进行同步
	 2.通信
	 使用ReentrantLock类的newCondition()方法可以获取Condition对象。
	 需要等待的时候使用Condition的await()方法，唤醒的时候用signal()方法。
	 不同的线程使用不同的Condition，这样就能区分唤醒的时候找那个线程了。
 */