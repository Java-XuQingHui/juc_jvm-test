package com.atguigu.juc;

import java.util.concurrent.CompletableFuture;

/**
 * <p>异步回调</p>
 *
 * @author xuqinghui
 * @create 2020/4/26 14:21
 */
public class CompletableFutureDemo {

	public static void main(String[] args) throws Exception{
		// 没有返回值异步回调
		CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
			System.out.println(Thread.currentThread().getName() + "操作完成 没有返回值");
		});
		completableFuture.get();

		// 有返回值异步回调
		CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
			System.out.println(Thread.currentThread().getName()+"\t completableFuture2");
			//int i = 10/0;
			return 1024;
		});

		completableFuture2.whenComplete((t,u)->{
			// 正常： t结果 u异常
			System.out.println("-------t="+t);
			System.out.println("-------u="+u);
		}).exceptionally(f->{
			// 异常 返回
			System.out.println("-----exception:"+f.getMessage());
			return 444;
		}).get();

	}

}
