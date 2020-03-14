package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatchGetMaterialRequest {
    private String type;

    private Integer offset;

    private Integer count;

    public BatchGetMaterialRequest(String type,
                                   Integer offset,
                                   Integer count) {
        this.type = type;
        this.offset = offset;
        this.count = count;
    }
}