package com.iteaj.network.device.server.aava.protocol;

/**
 * 曲目
 */
public enum AavaSongType {
    ;

    private String code;

    AavaSongType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
