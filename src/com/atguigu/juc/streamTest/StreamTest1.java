package com.atguigu.juc.streamTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description     Java 8 Streams API：对Stream分组和分区
 * @Author xuqinghui
 * @Date 2019/4/9 11:54
 **/
public class StreamTest1 {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<Employee>() {{
			add(new Employee(1, "Alice", "London", 200));
			add(new Employee(2, "Bob", "London", 150));
			add(new Employee(3, "Charles", "New York", 160));
			add(new Employee(4, "Dorothy", "Hong kong", 190));
		}};

		/* 常规分组方式 */
		/*Map<String, List<Employee>> result = new HashMap<>();
		for(Employee employee : list){
			String city = employee.getCity();
			List<Employee> temp = result.get(city);
			if(null == temp){
				temp = new ArrayList<>();
				result.put(city,temp);
			}
			temp.add(employee);
		}

		System.out.println(result.toString());*/

		/* Stream分组并统计 */
		/*Map<String, Long> result1 =
				list.stream().collect(groupingBy(Employee::getCity, counting()));
		System.out.println(result1);*/

		/* Stream分组并计算平均值*/
		/*Map<String, Double> avgSalesByCity =
				list.stream().collect(groupingBy(Employee::getCity,
						averagingInt(Employee::getSales)));

		System.out.println(avgSalesByCity);*/

		/* Stream分区并统计*/
		/*Map<Boolean, Map<String, Long>> result2 =
				list.stream().collect(partitioningBy(e -> e.getSales() > 150,
						groupingBy(Employee::getCity, counting())));
		System.out.println(result2);*/
	}

}
