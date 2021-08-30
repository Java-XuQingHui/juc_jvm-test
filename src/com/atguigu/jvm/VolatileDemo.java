package com.atguigu.jvm;

/**
 * <p>volatile保证可见性和禁止指令重排</p>
 *
 * @author xuqinghui
 * @create 2020/4/22 09:57
 */


class MyNumber {

	// volatile保证可见性
	volatile int number = 10;

	public void changeValue() {
		this.number = 1024;
	}
}

public class VolatileDemo {

	public static void main(String[] args) {
		MyNumber myNumber = new MyNumber();

		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "---come in---");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			myNumber.changeValue();
			System.out.println(Thread.currentThread().getName() + "\t" + myNumber.number);
		},"AAA").start();

		while(myNumber.number == 10) {
			// 需要一种通知机制，通知main线程，值已做修改
		}

		System.out.println(Thread.currentThread().getName() + "\t mission is over");
	}

}
