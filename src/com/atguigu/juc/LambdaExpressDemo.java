package com.atguigu.juc;

/**
 * <p>lambda表达式编程</p>
 *
 * @author xuqinghui
 * @create 2020/4/17 16:43
 */

// 函数式接口-函数式接口定义为仅含有一个抽象方法的接口
@FunctionalInterface
interface Foo {
	int add(int x, int y);

	default int mul(int x, int y) {
		return x * y;
	}

	static int div(int x, int y) {
		return x / y;
	}
}

public class LambdaExpressDemo {

	public static void main(String[] args) {
		// 拷贝小括号  写死右箭头  落地大括号
		Foo foo = (int x, int y) -> {
			System.out.println("add loading");
			return x + y;
		};

		System.out.println("加法：" + foo.add(3, 5));

		System.out.println("乘法：" + foo.mul(3, 5));

		System.out.println("除法：" + Foo.div(6, 2));

	}

}
