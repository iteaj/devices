package com.iteaj.network.device.server.aava.protocol;

/**
 * 曲目
 */
public enum AavaSongType {
    v1("01"),
    v2("02"),
    ;

    private String code;

    AavaSongType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
