package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetMaterialRequest {
    private String mediaId;

    public GetMaterialRequest(String mediaId) {
        this.mediaId = mediaId;
    }
}