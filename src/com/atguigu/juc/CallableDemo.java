package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>通过Callable实现多线程</p>
 *
 * @author xuqinghui
 * @create 2020/4/20 14:54
 */
// 实现Runnable和Callable接口的两个方式之间的区别：
//   1.实现方法不一样 2.异常不一样 3.返回值不一样
class MyThread implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("-----come in Callable-----");
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1024;
	}
}

public class CallableDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

		// 同一个FutureTask调用只调用一次，之后用缓存数据
		new Thread(futureTask,"A").start();
		new Thread(futureTask,"B").start();

		System.out.println(Thread.currentThread().getName() + "----计算完成----");

		// get方法放到最后
		System.out.println(futureTask.get());
	}

}

/**
 * 实现多线程的四种方式：
 * 	 1、继承Thread类创建线程
 * 	 2、实现Runnable接口
 * 	 3、实现Callable接口实现多线程
 * 	 4、通过线程池来实现多线程
*/
