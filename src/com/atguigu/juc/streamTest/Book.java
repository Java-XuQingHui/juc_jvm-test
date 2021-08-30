package com.atguigu.juc.streamTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>基础类</p>
 *
 * @author xuqinghui
 * @create 2020/4/25 14:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Book {

	// 链式编程 + 流式计算

	private Integer id;
	private String bookName;
	private Double price;

	public static void main(String[] args) {
		Book book = new Book();
		book.setId(1).setBookName("java编程思想").setPrice(128.5d);
		System.out.println(book);
	}

}

/**
 * 加了@Data注解的类，编译后会自动给我们加上下列方法：
 *   1.所有属性的get和set方法
	 2.toString方法
	 3.hashCode方法
	 4.equals方法
 */