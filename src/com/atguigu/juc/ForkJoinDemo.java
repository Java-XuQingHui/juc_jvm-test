package com.atguigu.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>分支合并框架</p>
 *
 * @author xuqinghui
 * @create 2020/4/26 10:06
 */

class MyTask extends RecursiveTask<Integer> {

	// 定义一个阈值
	private static final Integer ADJUST_VALUE = 10;

	private int begin;
	private int end;
	private int result;

	public MyTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		if((end - begin) <= ADJUST_VALUE) {
			for (int i = begin; i <= end ; i++) {
				result += i;
			}
		} else{
			int middle = (end + begin) / 2;
			MyTask task01 = new MyTask(begin, middle);
			MyTask task02 = new MyTask(middle+1, end);
			task01.fork();
			task02.fork();
			result = task01.join() + task02.join();
		}
		return result;
	}
}
public class ForkJoinDemo {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 1.创建一个资源类
		MyTask task = new MyTask(0, 100);

		// 2.创建一个分支合并池
		ForkJoinPool forkJoinPool = new ForkJoinPool();

		// 3.提交任务
		ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);

		// 4.获取结果
		System.out.println(forkJoinTask.get());

		// 5.关闭分支合并池
		forkJoinPool.shutdown();
	}

}
