package com.test.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.test.bean.User;
import com.test.util.MongoUtil;

@Repository
public class MongoDao {
	@Autowired
	private MongoTemplate mongoTemplate;

	public void add2(User user) throws Exception {

		Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
		// AdvancedDatastore dataStore = MongoUtil.getDataStore();
		 //long countAll = query.countAll(); System.out.println(countAll);
		 // User user = new User(); user.setName("sansan"); user.setAge(18);
		 // user.setSex("1"); dataStore.insert(user);
			query.field("name").equal("sansan");
			List<User> list = query.asList();
			System.out.println(list.get(0));
	}

	/**
	 * spring管理的mongodb连接
	 * @param user
	 * @throws Exception
	 */
	public void add(User user) throws Exception {
		DBCollection dbCollection = mongoTemplate.getCollection("User");
		BasicDBObject doc1 = new BasicDBObject();  
        doc1.put("age", 30); 
        dbCollection.insert(doc1);
	}
	public  void query(User user) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		DBCollection dbCollection = mongoTemplate.getCollection("User");
		DBCursor cursor = dbCollection.find();
		while (cursor.hasNext()) {
			User user2 = dbObject2Bean(cursor.next(),user);
			System.out.println(user2);
		}
	}

	public static <T> T dbObject2Bean(DBObject dbObject, T bean)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		if (bean == null) {
			return null;
		}
		Field[] fields = bean.getClass().getDeclaredFields();
		for (Field field : fields) {
			String varName = field.getName();
			Object object = dbObject.get(varName);
			if (object != null) {
				BeanUtils.setProperty(bean, varName, object);
			}
		}
		return bean;
	}
}
