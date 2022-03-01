package com.garylee.config.server.repository;

import com.garylee.config.server.model.LeeObjectConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-28 23:18
 * @description:
 */
@Service
@Slf4j
public class LeeObjectConfigRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     *
     * @param config
     * @return
     */
    public LeeObjectConfig insert(LeeObjectConfig config) {
        return mongoTemplate.insert(config);
    }

    /**
     * 全量查询
     *
     * @return
     */
    public List<LeeObjectConfig> queryAll() {
        return mongoTemplate.findAll(LeeObjectConfig.class);
    }

    /**
     * 更新
     *
     * @param config
     */
    public void update(LeeObjectConfig config) {
        mongoTemplate.findAndModify(
                Query.query(Criteria.where(LeeObjectConfig.Fields.key).is(config.getKey())),
                Update.update(LeeObjectConfig.Fields.value, config.getValue())
                        .set(LeeObjectConfig.Fields.description, config.getDescription())
                        .inc(LeeObjectConfig.Fields.version, 1)
                        .set(LeeObjectConfig.Fields.operatorUid, config.getOperatorUid())
                        .set(LeeObjectConfig.Fields.operatorName, config.getOperatorName())
                        .set(LeeObjectConfig.Fields.updateTime, new Date()),
                //FindAndModifyOptions.options().returnNew(true),
                LeeObjectConfig.class
        );
    }
}
