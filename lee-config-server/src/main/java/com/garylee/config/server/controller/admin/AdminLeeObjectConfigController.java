package com.garylee.config.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garylee.config.api.common.resp.LeeApiResp;
import com.garylee.config.server.model.LeeObjectConfig;
import com.garylee.config.server.model.LeeObjectConfigHistory;
import com.garylee.config.server.repository.LeeObjectConfigHistoryRepository;
import com.garylee.config.server.repository.LeeObjectConfigRepository;
import com.garylee.config.server.service.LeeObjectConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @Autowired
    private LeeObjectConfigService leeObjectConfigService;

    @GetMapping("/queryAllConfigsByPage")
    public LeeApiResp<IPage<LeeObjectConfig>> queryAllConfigsByPage(Page<LeeObjectConfig> page) {
        List<LeeObjectConfig> allConfigs = leeObjectConfigRepository.queryAll();

        //todo 物理分页，后续可以优化
        List<LeeObjectConfig> resultConfigs = allConfigs
                .stream()
                .skip((page.getCurrent() - 1) * page.getSize())
                .limit(page.getSize())
                .collect(Collectors.toList());

        //分页
        page.setCurrent(page.getCurrent());
        page.setPages((allConfigs.size() - 1) / page.getSize() + 1);
        page.setRecords(resultConfigs);
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
     * 新增/更新配置
     *
     * @param config
     * @return
     */
    @PostMapping("/addOrUpdateConfig")
    public LeeApiResp<LeeObjectConfig> addOrUpdateConfig(@RequestBody LeeObjectConfig config) {
        if (StringUtils.isBlank(config.getId())) {
            config.setId(UUID.randomUUID().toString());
            config.setVersion(1L);
            config.setFrequency(0L);
            return LeeApiResp.success(leeObjectConfigRepository.insert(config));
        } else {
            return LeeApiResp.success(leeObjectConfigService.updateConfig(config));
        }
    }

    /**
     * 删除配置
     *
     * @param id 配置唯一id
     * @return
     */
    @PostMapping("/deleteConfig")
    public LeeApiResp<LeeObjectConfig> deleteConfig(String id) {
        return LeeApiResp.success(leeObjectConfigRepository.delete(id));
    }

    /**
     * 分页查询历史配置(用来回滚等)
     *
     * @param key
     * @param page
     * @return
     */
    @GetMapping("/queryConfigHistories")
    public LeeApiResp<IPage<LeeObjectConfigHistory>> queryConfigHistories(String key,
                                                                          Page<LeeObjectConfigHistory> page) {
        List<LeeObjectConfigHistory> allConfigHistories = leeObjectConfigHistoryRepository.queryConfigHistoriesByKey(key);

        //todo 物理分页，后续可以优化
        List<LeeObjectConfigHistory> resultConfigHistories = allConfigHistories
                .stream()
                .skip((page.getCurrent() - 1) * page.getSize())
                .limit(page.getSize())
                .collect(Collectors.toList());

        //分页
        page.setCurrent(page.getCurrent());
        page.setPages((allConfigHistories.size() - 1) / page.getSize() + 1);
        page.setRecords(resultConfigHistories);
        page.setTotal(allConfigHistories.size());

        return LeeApiResp.success(page);
    }

    /**
     * 回滚配置
     *
     * @param key
     * @param configHistoryId 回滚目标id(也许可以用version)
     * @return
     */
    @PostMapping("/rollback")
    public LeeApiResp<Boolean> rollback(String key,
                                        String configHistoryId) {
        //todo 待实现
        return LeeApiResp.success(true);
    }

    //todo 搜索
}
