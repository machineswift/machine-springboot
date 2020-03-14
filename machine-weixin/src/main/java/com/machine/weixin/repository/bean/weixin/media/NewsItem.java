package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewsItem {
    private String title;

    private String thumb_media_id;

    private String show_cover_pic;

    private String author;

    private String digest;

    private String content;

    private String url;

    private String content_source_url;
}