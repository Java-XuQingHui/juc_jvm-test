package com.atguigu.jvm;

import java.util.Random;

/**
 * <p>堆参数调优</p>
 *
 * @author xuqinghui
 * @create 2020/4/21 16:23
 */
public class HeapParamDemo {

	public static void main(String[] args) {
//		// 返回java虚拟机视图使用的最大内存量
//		long maxMemory = Runtime.getRuntime().maxMemory();
//		// 返回java虚拟机中的内存总量
//		long totalMemory = Runtime.getRuntime().totalMemory();
//		System.out.println("-Xmx:MAX_MEMORY" + maxMemory + "字节、" + maxMemory / (double) 1024 / 1024 + "MB");
//		System.out.println("-Xms:TOTAL_MEMORY" + maxMemory + "字节、" + totalMemory / (double) 1024 / 1024 + "MB");

		// 模拟 Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
		String str = "www.atguigu.com" ;
		while(true)
		{
			str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
		}

	}

}
