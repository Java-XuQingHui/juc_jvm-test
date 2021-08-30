package com.atguigu.juc.streamTest;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @Author xuqinghui
 * @Date 2019/3/26 16:09
 **/
public class StreamTest2 {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("a","b");
		// 外部迭代
		for(String str : list){
			System.out.println(str);
		}

		// 内部迭代
		list.stream().forEach(System.out::println);

		/*
		 流通常由三部分构成：
			1. 数据源：数据源一般用于流的获取，比如本文开头那个过滤用户的例子中users.stream()方法。
			2. 中间处理：中间处理包括对流中元素的一系列处理，如：过滤（filter()），映射（map()），排序（sorted()）。
			3. 终端处理：终端处理会生成结果，结果可以是任何不是流值，如List<String>；也可以不返回结果，如stream.forEach(System.out::println)就是将结果打印到控制台中，并没有返回。
		*/
	}


}
