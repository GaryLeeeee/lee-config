package com.garylee.config.server.model.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author lijialin6
 * @date 2022/2/22 12:53 下午
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
@Document("test_user")
public class TestUser {
    private int id;
    private String name;
    private int age;
    private int sex;
}
