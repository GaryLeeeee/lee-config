package com.garylee.config.server.service;

import com.garylee.config.server.repository.LeeObjectConfigHistoryRepository;
import com.garylee.config.server.repository.LeeObjectConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
