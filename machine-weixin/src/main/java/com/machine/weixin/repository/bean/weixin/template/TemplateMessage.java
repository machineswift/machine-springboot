package com.machine.weixin.repository.bean.weixin.template;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
@NoArgsConstructor
public class TemplateMessage {
    private String touser;
    private String templateId;
    private String url;
    private MiniProgram miniprogram;
    private Map<String, FieldInfo> data;

    public void setMiniProgram(String appId, String pagePath) {
        this.miniprogram = new MiniProgram(appId, pagePath);
    }

    public void addField(String key, String value) {
        if (Objects.isNull(data)) {
            data = new HashMap<>();
        }
        this.data.put(key, new FieldInfo(value));
    }

    public void addField(String key, String value, String color) {
        if (Objects.isNull(data)) {
            data = new HashMap<>();
        }
        this.data.put(key, new FieldInfo(value, color));
    }

    @Data
    @NoArgsConstructor
    static class MiniProgram {
        private String appid;
        private String pagepath;

        public MiniProgram(String appid, String pagepath) {
            this.appid = appid;
            this.pagepath = pagepath;
        }
    }

    @Data
    @NoArgsConstructor
    static class FieldInfo {
        private String value;
        private String color;

        public FieldInfo(String value) {
            this.value = value;
        }

        public FieldInfo(String value, String color) {
            this.value = value;
            this.color = color;
        }
    }
}
