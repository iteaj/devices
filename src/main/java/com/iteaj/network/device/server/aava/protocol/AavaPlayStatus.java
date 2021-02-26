package com.iteaj.network.device.server.aava.protocol;

/**
 * 播放模式
 */
public enum AavaPlayStatus {

    repeat("00"), // 单曲循环
    p1("01"), // 播放次数 1
    p2("02"), // 播放次数 2
    p3("03"), // 播放次数 3
    p4("04"), // 播放次数 4
    p5("05"), // 播放次数 5
    p6("06"), // 播放次数 6
    p7("07"), // 播放次数 7
    p8("08"), // 播放次数 8
    p9("09"), // 播放次数 9
    p10("0A"), // 播放次数 10
    p11("0B"), // 播放次数 11
    p12("0C"), // 播放次数 12
    p13("0D"), // 播放次数 13
    p14("0E"), // 播放次数 14
    p15("0F"), // 播放次数 15
    p16("10"), // 播放次数 16
    ; // 播放次数 15

    private String code;

    AavaPlayStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
