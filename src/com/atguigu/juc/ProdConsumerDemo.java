package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>两个线程，操作一个初始值是0的变量，实现一个线程对变量加1，一个线程对变量减1，实现交替进行10轮</p>
 *
 * @author xuqinghui
 * @create 2020/4/18 17:58
 */

// 资源类
class AirCondition {
	private int number = 0;

	private Lock lock = new ReentrantLock();

	private Condition condition  = lock.newCondition();


	/************************Lock*********************/

	public void increment() throws Exception {
		lock.lock();

		try {
			// 1.循环并判断 防止虚假唤醒，其中多线程的交互判断必须使用while,不能使用if
			while(number != 0) {
				condition.await();
			}

			// 2.生产
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" +number);

			// 3.通知
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() throws Exception {
		lock.lock();

		try {
			// 1.循环并判断
			while(number == 0) {
				condition.await();
			}

			// 2.消费
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" +number);

			// 3.通知
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/************************synchronized*********************/

	/*public synchronized void increment() throws Exception {
		// 1.循环并判断 防止虚假唤醒，其中多线程的交互判断必须使用while,不能使用if
		while(number != 0) {
			this.wait();
		}

		// 2.生产
		number++;
		System.out.println(Thread.currentThread().getName() + "\t" +number);

		// 3.通知
		this.notifyAll();
	}

	public  synchronized void decrement() throws Exception {
		// 1.循环并判断
		while(number == 0) {
			this.wait();
		}

		// 2.消费
		number--;
		System.out.println(Thread.currentThread().getName() + "\t" +number);

		// 3.通知
		this.notifyAll();
	}*/

}
public class ProdConsumerDemo {

	// 线程操作资源类
	public static void main(String[] args) {
		AirCondition airCondition = new AirCondition();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				try {
					airCondition.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "A").start();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				try {
					airCondition.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "B").start();


		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				try {
					airCondition.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "C").start();

		new Thread(() -> {
			for (int i = 1; i <= 10 ; i++) {
				try {
					airCondition.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "D").start();
	}

}
