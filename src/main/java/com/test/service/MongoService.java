package com.test.service;

import com.test.bean.User;

import java.util.List;

public interface MongoService {

	public void add(User user);

	public List<User> queryUser();

	public void remove(String userId);

	public int checkname(String name);
}
