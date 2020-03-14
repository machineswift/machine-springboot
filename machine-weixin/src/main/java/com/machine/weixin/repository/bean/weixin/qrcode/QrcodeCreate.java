package com.machine.weixin.repository.bean.weixin.qrcode;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
@Data
@NoArgsConstructor
public abstract class QrcodeCreate {
    private String actionName;
    protected ActionInfo actionInfo = new ActionInfo();

    @Data
    @NoArgsConstructor
    static class ActionInfo {
        private Scene scene = new Scene();

        public void setSceneId(Integer sceneId) {
            scene.setSceneId(sceneId);
        }

        public void setSceneStr(String sceneStr) {
            scene.setSceneStr(sceneStr);
        }
    }

    @Getter
    @NoArgsConstructor
    static class Scene {
        private String sceneStr;
        private Integer sceneId;

        public Scene(String sceneStr) {
            this.sceneStr = sceneStr;
        }

        public void setSceneId(Integer sceneId) {
            if (Objects.nonNull(sceneId)) {
                throw new IllegalStateException("already set sceneStr");
            }
            this.sceneId = sceneId;
        }

        public void setSceneStr(String sceneStr) {
            if (Objects.nonNull(sceneId)) {
                throw new IllegalStateException("already set sceneId");
            }
            this.sceneStr = sceneStr;
        }
    }
}
