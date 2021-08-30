package com.atguigu.juc.streamTest;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * <p>函数式编程</p>
 *
 * @author xuqinghui
 * @create 2020/4/25 16:01
 */
public class FunctionTest {


	public static void main(String[] args) {

		// java内置核心四大函数式接口 函数式lambda表达式写法

		// 1.函数型接口Function<String,Integer>
		Function<String,Integer> function = s -> {return s.length(); };
		System.out.println(function.apply("abc"));

		/*Function<String,Integer> function = new Function<String, Integer>() {
			@Override
			public Integer apply(String s) {
				return 1024;
			}
		};*/

		// 2.断定型接口 Predicate<String>
		Predicate<String> predicate = s -> { return s.isEmpty(); };
		System.out.println(predicate.test("java"));

		/*Predicate<String> predicate = new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return false;
			}
		};*/

		// 3.消费型接口 Consumer<String>
		Consumer<String> consumer = s -> { System.out.println(s); };
		consumer.accept("hello world");
		/*Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String s) {

			}
		};*/

		// 4.供给型接口 Supplier<String>
		Supplier<String> supplier = () -> {return "atguigu"; };
		System.out.println(supplier.get());
		/*Supplier<String> supplier = new Supplier<String>() {
			@Override
			public String get() {
				return null;
			}
		};*/

	}
}
