package com.atguigu.juc.volatileTest;

/**
 * @Description  实现可见性
 * @Author xuqinghui
 * @Date 2019/4/11 14:44
 **/
public class VolatileTest1 {
	int a = 1;
	int b = 2;

	public void change(){
		a = 3;
		b = a;
	}

	public void print(){
		System.out.println("b="+b+";a="+a);
	}

	public static void main(String[] args) {
		while (true){
			final VolatileTest1 test = new VolatileTest1();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.change();
				}
			}).start();

			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					test.print();
				}
			}).start();
		}
	}
}

/*
程序运行的三种结果:b=3;a=3 或 b=2;a=1 或 b=3;a=1
那b=3;a=1的结果是怎么出来的？原因就是第一个线程将值a=3修改后，但是对第二个线程是不可见的，所以才出现这一结果。
如果将a和b都改成volatile类型的变量再执行，则再也不会出现b=3;a=1的结果了。
*/
