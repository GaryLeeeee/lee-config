package com.garylee.config.server.repository.test;

import com.garylee.config.server.domain.test.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lijialin6
 * @date 2022/2/22 12:54 下午
 * @desc
 */
@Repository
public class TestUserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<TestUser> queryUsersByAge(int age) {
        return mongoTemplate.find(Query.query(Criteria.where(TestUser.Fields.age).is(age)), TestUser.class);
    }

    public void addUser(TestUser testUser) {
        mongoTemplate.insert(testUser);
    }
}
