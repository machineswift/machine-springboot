package com.machine.weixin.repository.bean.weixin.qrcode;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class QrcodeCreate {
    private String actionName;
    private Integer expireSeconds;
    protected ActionInfo actionInfo = new ActionInfo();

    @Data
    @NoArgsConstructor
    public class ActionInfo {
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
    public class Scene {
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
