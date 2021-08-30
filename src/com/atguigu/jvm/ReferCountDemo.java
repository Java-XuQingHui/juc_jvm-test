package com.atguigu.jvm;

/**
 * <p>引用计数法相互引用造成泄漏-</p>
 *
 * @author xuqinghui
 * @create 2019/7/18 10:30
 */
public class ReferCountDemo {
	public Object instance;
	public ReferCountDemo(String name) {}

	public static void main(String[] args) {
		ReferCountDemo a = new ReferCountDemo("obja");
		ReferCountDemo b = new ReferCountDemo("objb");

		a.instance = b;
		b.instance = a;

		a = null;
		b = null;
	}

}
