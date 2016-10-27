package com.test.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.test.bean.User;
import com.test.util.MongoUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Repository
public class MongoDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void add(User user) throws Exception {

        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        AdvancedDatastore dataStore = MongoUtil.getDataStore();
        dataStore.insert(user);
    }
    public List<User> queryUser(){
        Query<User> query = MongoUtil.getDataStore().createQuery(User.class);
        return query.asList();
    }
    /**
     * spring管理的mongodb连接
     *
     * @param user
     * @throws Exception
     */
    public void add2(User user) throws Exception {
        DBCollection dbCollection = mongoTemplate.getCollection("User");
        BasicDBObject doc1 = new BasicDBObject();
        doc1.put("name", user.getName());
        doc1.put("age", user.getAge());
        doc1.put("sex", user.getSex());
        dbCollection.insert(doc1);
    }

    public void query(User user) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        DBCollection dbCollection = mongoTemplate.getCollection("User");
        DBCursor cursor = dbCollection.find();
        while (cursor.hasNext()) {
            User user2 = dbObject2Bean(cursor.next(), user);
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
