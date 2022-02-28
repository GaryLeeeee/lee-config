package com.garylee.config.server;

import com.garylee.config.server.domain.test.TestUser;
import com.garylee.config.server.repository.test.TestUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-27 18:50
 * @description:
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestUserRepositoryTest {
    @Autowired
    private TestUserRepository testUserRepository;

    @Test
    public void queryUsersByAge() {
        System.out.println(testUserRepository.queryUsersByAge(18));
    }

    @Test
    public void addUser() {
        TestUser testUser = new TestUser();
        testUser.setId(111);
        testUser.setName("小明");
        testUser.setAge(18);
        testUser.setSex(1);

        testUserRepository.addUser(testUser);
    }
}
