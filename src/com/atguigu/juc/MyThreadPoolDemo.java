package com.atguigu.juc;

import java.util.concurrent.*;

/**
 * <p>线程池</p>
 *
 * @author xuqinghui
 * @create 2020/4/24 17:51
 */
public class MyThreadPoolDemo {

	public static void main(String[] args) {
		// 获取cpu核心数
		System.out.println("----获取cpu核心数---- " + Runtime.getRuntime().availableProcessors() + "个");

		// 通过Executors默认方式创建的线程池不推荐使用
		/**
		 * 【强制】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样
		   的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
		   说明：Executors 各个方法的弊端：
				 1）newFixedThreadPool 和 newSingleThreadExecutor:
				 主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至 OOM。
				 2）newCachedThreadPool 和 newScheduledThreadPool:
				 主要问题是线程数最大数是 Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至 OOM。
		 */

		// initPool();

		// 通过ThreadPoolExecutor的方式来创建线程池
		// 其中maximumPoolSize设置的规则是：Classloader
		// 如果是CPU密集型 就是 线程数= CPU核数+1
		// 如果是IO密集型 就是 线程数= CPU核心数 / (1-阻塞系数)
		// 		这个阻塞系数一般为0.8~0.9之间，也可以取0.8或者0.9。
		ExecutorService threadPool = new ThreadPoolExecutor(
				2,
				5,
				2L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());

		try {
			// 模拟一定数量的顾客过来办理业务，目前只有5个工作人员提供服务
			// 其中最大线程数是maximumPoolSize 最大容量数是maximumPoolSize+LinkedBlockingQueue
			for (int i = 1; i <= 10 ; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t 办理业务");
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	}

	public static void initPool() {
		// 三种方式
		// 一池5个工作线程，类似一个银行有5个受理窗口
		ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
		// 一池1个工作线程，类似于一个银行有1个受理窗口
		 ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
		// 一池N个工作线程，类似于一个银行有N个受理窗口
		ExecutorService threadPool3 = Executors.newCachedThreadPool();

		try {
			// 模拟有10个顾客过来办理业务，目前只有5个工作人员提供服务
			for (int i = 1; i <= 10 ; i++) {
				threadPool3.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t 办理业务");
				});
				// 模拟网络，暂停一下
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool3.shutdown();
		}
	}

}
