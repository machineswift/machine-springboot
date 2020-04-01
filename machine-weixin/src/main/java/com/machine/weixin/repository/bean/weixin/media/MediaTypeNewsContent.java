package com.machine.weixin.repository.bean.weixin.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class MediaTypeNewsContent {

    @JsonProperty("update_time")
    private Timestamp updateTime;

    @JsonProperty("create_time")
    private Timestamp createTime;

    @JsonProperty("news_item")
    private List<NewsItem> newsItem;

}