package com.machine.weixin.repository.bean.weixin.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class MediaTypeNewsItem {

    @JsonProperty("media_id")
    private String mediaId;

    private MediaTypeNewsContent content;

    @JsonProperty("update_time")
    private Timestamp updateTime;
}