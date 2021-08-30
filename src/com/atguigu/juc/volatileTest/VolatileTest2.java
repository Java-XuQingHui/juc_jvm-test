package com.atguigu.juc.volatileTest;

/**
 * @Description  防止重排序
 * @Author xuqinghui
 * @Date 2019/4/11 14:41
 **/
public class VolatileTest2 {
	public static volatile VolatileTest2 volatileTest2;

	/**
	 * 构造函数私有，禁止外部实例化
	 */
	private VolatileTest2() {};

	public static VolatileTest2 getInstance() {
		if (volatileTest2 == null) {
			synchronized (volatileTest2) {
				if (volatileTest2 == null) {
					volatileTest2 = new VolatileTest2();
				}
			}
		}
		return volatileTest2;
	}
}


/*
现在我们分析一下为什么要在变量singleton之间加上volatile关键字。要理解这个问题，先要了解对象的构造过程，实例化一个对象其实可以分为三个步骤：

　　（1）分配内存空间。

　　（2）初始化对象。

　　（3）将内存空间的地址赋值给对应的引用。

但是由于操作系统可以对指令进行重排序，所以上面的过程也可能会变成如下过程：

　　（1）分配内存空间。

　　（2）将内存空间的地址赋值给对应的引用。

　　（3）初始化对象
如果是这个流程，多线程环境下就可能将一个未初始化的对象引用暴露出来，从而导致不可预料的结果。因此，为了防止这个过程的重排序，我们需要将变量设置为volatile类型的变量。*/
