package com.garylee.config.server.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author lijialin6
 * @date 2022/2/22 12:54 下午
 * @desc
 */
@Repository
public class UserRepository {
    @Autowired
    private MongoTemplate mongoTemplate;
}
