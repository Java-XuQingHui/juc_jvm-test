package com.atguigu.juc.streamTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>流式计算测试</p>
 *
 * @author xuqinghui
 * @create 2020/4/25 14:22
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
	private Integer id;
	private String userName;
	private Integer age;
}

public class StreamTest3 {

	public static void main(String[] args) {
//		User u1 = new User(11, "a", 23);
//		User u2 = new User(12, "b", 24);
//		User u3 = new User(13, "c", 22);
//		User u4 = new User(14, "d", 28);
//		User u5 = new User(16, "e", 26);
//
//		List<User> userLists = Arrays.asList(u1, u2, u3, u4, u5);
//
//		// 源头=>中间流水线=>结果
//		// 筛选出id为偶数并且年龄大于24并且用户名转为大写并且用户名字母倒序，只输出一个用户名字
//		userLists.stream()
//				.filter(u -> {
//					return u.getId()%2 == 0;
//				})
//				.filter(t -> {
//					return t.getAge() > 24;
//				})
//				.map(m -> {
//					return m.getUserName().toUpperCase();
//				})
//				.sorted((o1,o2) -> {
//					return o2.compareTo(o1);
//		 		})
//				.limit(1)
//				.forEach(System.out::println);

		map();

	}


	// map操作
	public static void map() {
		List<Integer> list = Arrays.asList(1,2,3);
		list = list.stream().map(x -> { return x * 2;}).collect(Collectors.toList());
		for(Integer element : list) {
			System.out.println(element);
		}
	}
}
