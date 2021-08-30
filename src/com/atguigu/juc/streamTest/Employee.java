package com.atguigu.juc.streamTest;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author xuqinghui
 * @Date 2019/4/9 11:55
 **/
public class Employee implements Serializable{


	private static final long serialVersionUID = -8347365393897047156L;

	private Integer id;
	private String name;
	private String city;
	private Integer sales;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Employee() {
	}

	public Employee(Integer id, String name, String city, Integer sales) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.sales = sales;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", city='" + city + '\'' +
				", sales=" + sales +
				'}';
	}
}
