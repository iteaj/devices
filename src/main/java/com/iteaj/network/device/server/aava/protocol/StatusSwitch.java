package com.iteaj.network.device.server.aava.protocol;

/**
 * 0:声音和闪光全部关闭
 * 1: 声音 1+闪光
 * 2:闪光;
 * 3:声音 1
 * 4:声音 2+闪光
 * 5:声音 2
 */
public enum StatusSwitch {

    关闭("00"),
    声音1闪光("01"),
    闪光("02"),
    声音1("03"),
    声音2闪光("04"),
    声音2("05");

    public String code;

    StatusSwitch(String code) {
        this.code = code;
    }
}
