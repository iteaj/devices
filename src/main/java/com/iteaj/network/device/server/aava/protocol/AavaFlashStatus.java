package com.iteaj.network.device.server.aava.protocol;

/**
 * 闪光状态
 */
public enum AavaFlashStatus {

    关闭("00"),
    频率1("01"),
    频率2("02"),
    频率("03"),
    频率4("04"),
    频率5("05"),
    一长一短("06"),
    一长二短("07");

    private String code;

    AavaFlashStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
