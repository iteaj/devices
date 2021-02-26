package com.iteaj.network.device.server.aava.protocol;

/**
 * 音量状态
 */
public enum AavaVolumeStatus {
    close("00"),
    v1("01"),// 一档音量, 以此类推
    v2("02"),
    v3("03"),
    v4("04"),
    v5("05"),
    v6("06"),
    v7("07"),
    v8("08"),
    ;

    private String code;

    AavaVolumeStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
