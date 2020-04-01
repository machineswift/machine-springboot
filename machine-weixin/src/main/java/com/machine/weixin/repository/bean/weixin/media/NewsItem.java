package com.machine.weixin.repository.bean.weixin.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsItem {
    private String title;

    @JsonProperty("thumb_media_id")
    private String thumbMediaId;

    @JsonProperty("show_cover_pic")
    private String showCoverPic;

    private String author;

    private String digest;

    private String content;

    private String url;

    @JsonProperty("content_source_url")
    private String contentSourceUrl;
}