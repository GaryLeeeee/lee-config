package com.garylee.config.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garylee.config.api.common.resp.LeeApiResp;
import com.garylee.config.server.model.LeeObjectConfig;
import com.garylee.config.server.repository.LeeObjectConfigHistoryRepository;
import com.garylee.config.server.repository.LeeObjectConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-28 23:14
 * @description: 通用配置-管理后台接口
 */
@RestController
@Slf4j
@RequestMapping("/admin/lee-config")
public class AdminLeeObjectConfigController {
    @Autowired
    private LeeObjectConfigRepository leeObjectConfigRepository;
    @Autowired
    private LeeObjectConfigHistoryRepository leeObjectConfigHistoryRepository;

    @GetMapping("/queryAllConfigsByPage")
    public LeeApiResp<IPage<LeeObjectConfig>> queryAllConfigsByPage(Page<LeeObjectConfig> page) {
        List<LeeObjectConfig> allConfigs = leeObjectConfigRepository.queryAll();

        //分页
        page.setCurrent(page.getCurrent());
        page.setPages((allConfigs.size() - 1) / page.getSize() + 1);
        page.setRecords(allConfigs);
        page.setTotal(allConfigs.size());

        return LeeApiResp.success(page);
    }

    /**
     * 查询所有配置
     *
     * @return
     */
    @GetMapping("/queryAllConfigs")
    public LeeApiResp<List<LeeObjectConfig>> queryAllConfigs() {
        return LeeApiResp.success(leeObjectConfigRepository.queryAll());
    }

    /**
     * 新增配置
     *
     * @param config
     * @return
     */
    @PostMapping("/addConfig")
    public LeeApiResp<LeeObjectConfig> addConfig(LeeObjectConfig config) {
        return LeeApiResp.success(leeObjectConfigRepository.insert(config));
    }

    /**
     * 更新配置
     *
     * @param config
     * @return
     */
    @PostMapping("/updateConfig")
    public LeeApiResp<Boolean> updateConfig(LeeObjectConfig config) {
        leeObjectConfigRepository.update(config);
        return LeeApiResp.success(true);
    }

    //todo 搜索
}
