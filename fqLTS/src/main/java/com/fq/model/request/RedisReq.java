package com.fq.model.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RedisReq {
    // 用户id
    @NotEmpty(message = "用户id为空")
    String id;

    // 次数
    String size;

}
