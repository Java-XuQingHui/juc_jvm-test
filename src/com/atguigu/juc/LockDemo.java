package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

/**
 * <p>lock</p>
 *
 * @author xuqinghui
 * @create 2020/4/18 15:21
 */


class Phone {
	public static synchronized void sendEmail() throws Exception {
		// 暂停4秒
		TimeUnit.SECONDS.sleep(4);
		System.out.println("sendEmail....");
	}

	public static synchronized void sendSms() throws Exception {
		System.out.println("sendSms....");
	}

	public void say() {
		System.out.println("hello...");
	}
}


/**
 * 1. 标准访问 先打印邮件还是短信
 * 2. 假如暂停4秒 先打印邮件还是短信
 *
 * 由上面得出结论：
 * 	 一个对象里面如果有多个synchronized方法，某一个时刻内，只要有一个线程去调用其中一个synchronized方法，
 * 	 其他线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法，这里锁的是
 * 	 当前对象this，被锁定之后，其他线程都不能进入到当前对象的其他synchronized方法。
 *
 * 3. 新增普通方法 先打印邮件还是普通方法
 * 由上面得出结论： 普通方法和同步锁无关
 *
 * 4. 两个资源实例 先打印邮件还是短信
 * 由上面得出结论： 两个资源类，不是竞争同一个东西，不是同一把锁
 *
 * 5. 两个静态同步方法，同一个资源实例 先打印邮件还是短信
 * 6. 两个静态同步方法，两个资源实例 先打印邮件还是短信
 * 7. 一个静态同步方法，一个非静态同步方法，同一个资源实例 先打印邮件还是短信
 * 8. 一个静态同步方法，一个非静态同步方法，两个资源实例 先打印邮件还是短信
 *
 * 由上面得出结论：synchronized实现同步方法的基础： Java中的每一个对象都可以作为锁
 * 具体表现为以下3中形式：
 * 	对于普通同步方法，锁的是当前实例对象，锁的当前对象this
 * 	对于同步代码块，锁的synchronized配置的对象
 * 	对于静态同步方法，锁的是当前类的Class对象
 *
 * 	总的来讲是：对象锁和全局锁
 *
 *
 * */
public class LockDemo {

	public static void main(String[] args) throws InterruptedException {
		Phone phone = new Phone();

		Phone phone2 = new Phone();

		new Thread(() -> {
			try {
				phone.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"A").start();

		Thread.sleep(4000);

		new Thread(() -> {
			try {
				//phone.sendSms();
				// phone.say();
				 phone2.sendSms();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"B").start();
	}

}
