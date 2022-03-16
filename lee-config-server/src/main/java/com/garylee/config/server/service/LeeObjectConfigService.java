package com.garylee.config.server.service;

import com.garylee.config.server.model.LeeObjectConfig;
import com.garylee.config.server.model.LeeObjectConfigHistory;
import com.garylee.config.server.repository.LeeObjectConfigHistoryRepository;
import com.garylee.config.server.repository.LeeObjectConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author: GaryLeeeee
 * @date: 2022-03-01 21:58
 * @description: 通用配置service
 */
@Service
@Slf4j
public class LeeObjectConfigService {
    @Autowired
    private LeeObjectConfigRepository leeObjectConfigRepository;
    @Autowired
    private LeeObjectConfigHistoryRepository leeObjectConfigHistoryRepository;

    /**
     * 更新配置
     *
     * @param newConfig
     * @return
     */
    public LeeObjectConfig updateConfig(LeeObjectConfig newConfig) {
        //获取老配置
        LeeObjectConfig oldConfig = leeObjectConfigRepository.queryByKey(newConfig.getKey());

        //更新新配置
        leeObjectConfigRepository.update(newConfig);
        //更新配置历史表
        Date now = new Date();
        LeeObjectConfigHistory configHistory = new LeeObjectConfigHistory();
        configHistory.setId(UUID.randomUUID().toString());
        configHistory.setKey(oldConfig.getKey());
        configHistory.setValue(oldConfig.getValue());
        configHistory.setDescription(oldConfig.getDescription());
        configHistory.setVersion(oldConfig.getVersion());
        configHistory.setFrequency(oldConfig.getFrequency());
        configHistory.setOperatorUid(oldConfig.getOperatorUid());
        configHistory.setOperatorName(oldConfig.getOperatorName());
        configHistory.setCreateTime(now);
        configHistory.setUpdateTime(now);
        leeObjectConfigHistoryRepository.insert(configHistory);

        return newConfig;
    }
}
