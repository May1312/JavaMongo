package com.test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.User;
import com.test.dao.MongoDao;
import com.test.service.MongoService;

import java.util.List;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongodab;

	public void add(User user) {
		try {
			mongodab.add(user);
			System.out.print(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<User> queryUser() {
		List<User> users = mongodab.queryUser();
		if (users!=null){
			return users;
		}
		return null;
	}

}
