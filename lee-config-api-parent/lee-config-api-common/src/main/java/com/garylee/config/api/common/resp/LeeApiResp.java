package com.garylee.config.api.common.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author: GaryLeeeee
 * @date: 2022-02-28 23:43
 * @description: 通用resp
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeeApiResp<T> implements Serializable {
    private static final long serialVersionUID = 5463473879645600851L;

    private int code;
    private String message;
    private T data;

    public boolean isSuccess() {
        return this.getCode() == LeeReturnCode.SUCCESS;
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public static <T> LeeApiResp<T> success(T data) {
        LeeApiResp<T> resp = new LeeApiResp<T>();
        resp.setCode(LeeReturnCode.SUCCESS);
        resp.setMessage("处理成功");
        resp.setData(data);
        return resp;
    }
}
