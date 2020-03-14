package com.machine.weixin.repository.bean.weixin.media;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MediaCountResponse {
    private Integer voice_count;

    private Integer video_count;

    private Integer image_count;

    private Integer news_count;

    public MediaCountResponse(Integer voice_count,
                              Integer video_count,
                              Integer image_count,
                              Integer news_count) {
        this.voice_count = voice_count;
        this.video_count = video_count;
        this.image_count = image_count;
        this.news_count = news_count;
    }
}