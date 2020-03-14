package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
public class MediaTypeNewsContent {

    private Timestamp update_time;

    private Timestamp create_time;

    private List<NewsItem> news_item;

}