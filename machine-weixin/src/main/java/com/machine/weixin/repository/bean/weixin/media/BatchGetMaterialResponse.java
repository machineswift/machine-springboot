package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BatchGetMaterialResponse {

    private Integer total_count;

    private Integer item_count;

    private List<MediaTypeNewsItem> item;
}