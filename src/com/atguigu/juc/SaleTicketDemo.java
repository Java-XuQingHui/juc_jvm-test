package com.atguigu.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>模拟三个售票员卖30张票</p>
 *
 * @author xuqinghui
 * @create 2020/4/15 11:00
 */

// 先定义一个资源类 = 实例变量 + 实例方法
class Ticket {
	// 定义票的数量
	private int number = 30;

	// 定义一个可重入锁
	Lock lock = new ReentrantLock();

	// 卖票方法
	public void sale() {
		lock.lock();
		try {
			if(number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩：" + number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}
public class SaleTicketDemo {

	public static void main(String[] args) {
		// 1.先创建一个资源类
		Ticket ticket = new Ticket();


		// 2.操作资源类
		// public Thread(Runnable target, String name)

		// Thread运行start方法之后，不会立马启动，
		// 只是进入就绪状态，调度是和底层的操作系统和cpu有关系


		new Thread(() -> { for (int i = 0; i <= 40; i++) { ticket.sale(); } }, "A").start();
		new Thread(() -> { for (int i = 0; i <= 40; i++) { ticket.sale(); } }, "B").start();
		new Thread(() -> { for (int i = 0; i <= 40; i++) { ticket.sale(); } }, "C").start();

		/*new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "A").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "B").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i <= 40; i++) {
					ticket.sale();
				}
			}
		}, "C").start();*/
	}

}
