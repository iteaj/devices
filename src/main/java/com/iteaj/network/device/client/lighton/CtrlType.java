package com.iteaj.network.device.client.lighton;

public enum CtrlType {
    查询("4c50%s551001003000000b0a000056aa"), // 查询设备信息
    系统开("4c50%s551001003000000B15030165AA"),
    系统关("4c50%s551001003000000B15000162AA"),
    主音量加("4c50%s551001003000000B5A0005ABAA"),
    主音量减("4c50%s551001003000000B5B0005ACAA"),
    电脑开("4c50%s551001033000000B5000009FAA"),
    电脑关("4c50%s551001033000000B510000A0AA"),
    投影机开("4c50%s551001033000000B500100A0AA"),
    投影机关("4c50%s551001033000000B510100A1AA"),
    ;
    public String hex;

    CtrlType(String hex) {
        this.hex = hex;
    }
}
