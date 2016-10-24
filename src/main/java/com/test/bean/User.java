package com.test.bean;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="User")
public class User implements Serializable{

	private static final long serialVersionUID = 8028989597226068116L;

	private String name;
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	@Id
	private String id;
	private int age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
