package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MediaTypeNewsItem {
    private String media_id;

    private MediaTypeNewsContent content;

    private Timestamp update_time;
}