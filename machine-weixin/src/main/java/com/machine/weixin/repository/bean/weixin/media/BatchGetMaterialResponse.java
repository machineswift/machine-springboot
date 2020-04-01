package com.machine.weixin.repository.bean.weixin.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BatchGetMaterialResponse {

    @JsonProperty("total_count")
    private Integer totalCount;

    @JsonProperty("item_count")
    private Integer itemCount;

    private List<MediaTypeNewsItem> item;
}