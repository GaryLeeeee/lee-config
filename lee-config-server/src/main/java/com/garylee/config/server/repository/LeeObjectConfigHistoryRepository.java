package com.garylee.config.server.repository;

import com.garylee.config.server.model.LeeObjectConfig;
import com.garylee.config.server.model.LeeObjectConfigHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-28 23:19
 * @description:
 */
@Service
@Slf4j
public class LeeObjectConfigHistoryRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 新增
     *
     * @param config
     * @return
     */
    public LeeObjectConfigHistory insert(LeeObjectConfigHistory config) {
        return mongoTemplate.insert(config);
    }

    /**
     * 全量查询
     *
     * @return
     */
    public List<LeeObjectConfigHistory> queryAll() {
        return mongoTemplate.findAll(LeeObjectConfigHistory.class);
    }

    public List<LeeObjectConfigHistory> queryConfigHistoriesByKey(String key) {
        List<LeeObjectConfigHistory> configHistories = mongoTemplate.find(
                Query.query(Criteria.where(LeeObjectConfigHistory.Fields.key).is(key)),
                LeeObjectConfigHistory.class
        );

        //todo 看看是否需要排序
        return configHistories;
    }
}
