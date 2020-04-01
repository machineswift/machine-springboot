package com.machine.weixin.service.impl;

import java.util.Optional;

public class SceneMessage {

    public enum Type {
        WEIXIN_TEMPLATE,
        WEIXIN_MEDIA
    }

    private final Type type;

    private SceneMessage(Type type) {
        this.type = type;
    }

    public static SceneMessage of(Type type) {
        return new SceneMessage(type);
    }

    public String pack(String message) {
        if (message == null) message = "";
        return type.name() + message;
    }

    public Optional<String> unpack(String message) {
        if (message == null)
            return Optional.empty();
        int index = message.indexOf(type.name());
        if (index != 0)
            return Optional.empty();
        String body = message.substring(type.name().length());
        return Optional.of(body);
    }

}


