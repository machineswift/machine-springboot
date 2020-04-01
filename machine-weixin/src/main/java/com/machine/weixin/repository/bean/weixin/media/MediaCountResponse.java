package com.machine.weixin.repository.bean.weixin.media;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaCountResponse {

    @JsonProperty("voice_count")
    private Integer voiceCount;

    @JsonProperty("video_count")
    private Integer videoCount;

    @JsonProperty("image_count")
    private Integer imageCount;

    @JsonProperty("news_count")
    private Integer newsCount;

    public MediaCountResponse(Integer voiceCount,
                              Integer videoCount,
                              Integer imageCount,
                              Integer newsCount) {
        this.voiceCount = voiceCount;
        this.videoCount = videoCount;
        this.imageCount = imageCount;
        this.newsCount = newsCount;
    }
}