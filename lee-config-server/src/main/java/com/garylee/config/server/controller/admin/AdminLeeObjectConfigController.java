package com.garylee.config.server.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.garylee.config.api.common.resp.LeeApiResp;
import com.garylee.config.server.model.LeeObjectConfig;
import com.garylee.config.server.model.LeeObjectConfigHistory;
import com.garylee.config.server.repository.LeeObjectConfigHistoryRepository;
import com.garylee.config.server.repository.LeeObjectConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
