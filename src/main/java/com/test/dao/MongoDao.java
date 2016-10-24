package com.test.dao;

import java.util.List;

import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.test.bean.User;
import com.test.util.MongoUtil;


@Repository
public class MongoDao {
	@Autowired
	private MongoTemplate mongoTemplate; 
	
	public void add(User user) throws Exception{
		
		Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
		//AdvancedDatastore dataStore = MongoUtil.getDataStore();
		/*long countAll = query.countAll();
		System.out.println(countAll);*/
		/*User user = new User();
		user.setName("sansan");
		user.setAge(18);
		user.setSex("1");
		dataStore.insert(user);*/
		query.field("name").equal("sansan");
		List<User> list = query.asList();
		System.out.println(list.get(0));
	}
}
