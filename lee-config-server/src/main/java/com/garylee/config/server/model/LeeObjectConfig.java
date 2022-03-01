package com.garylee.config.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-28 22:43
 * @description: 通用配置
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
@Document(collection = "lee_object_config")
public class LeeObjectConfig implements Serializable {
    private static final long serialVersionUID = 1860198788929252486L;

    /**
     * 唯一ID
     */
    private String id;

    /**
     * 键(key-value)
     */
    private String key;

    /**
     * 值(key-value)
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 版本(每次更新+1)
     */
    private Long version;

    /**
     * 使用频率(每次使用+1)
     */
    private Long frequency;

    /**
     * 操作者uid
     */
    private Long operatorUid;

    /**
     * 操作者名称
     */
    private String operatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
