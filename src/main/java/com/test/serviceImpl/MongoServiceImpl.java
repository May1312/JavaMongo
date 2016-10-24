package com.test.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.bean.User;
import com.test.dao.MongoDao;
import com.test.service.MongoService;

@Service
public class MongoServiceImpl implements MongoService {
	
	@Autowired
	private MongoDao mongodab;

	public void add(User user) {
		try {
			mongodab.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
