package com.iteaj.network.device.server.aava;

import com.iteaj.network.ProtocolType;

public enum AavaProtocolType implements ProtocolType {
    Heart("心跳", "", "", ""), // 心跳包
    Read_Device_Id("读取设备id", "01","3000", "000F"),
    Switch("开/关设备", "10", "001A", "0001"),
    ;

    private String desc; // 协议类型描述
    private String code; // 十六进制的功能代码(十六进制)
    private String start; // 起始地址 -> 从第几个字节开始读取或者设置(十六进制)
    private String num; // 要读取或者设置的字节数(十六进制)

    AavaProtocolType(String desc, String code, String start, String num) {
        this.desc = desc;
        this.code = code;
        this.start = start;
        this.num = num;
    }

    public static AavaProtocolType instance(String code) {
        switch (code) {
            case "01": return Read_Device_Id;
            case "10": return Switch;
            default:
                throw new IllegalStateException("找不到对应的协议类型");
        }
    }

    @Override
    public AavaProtocolType getType() {
        return this;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    public String getCode() {
        return code;
    }

    public String getStart() {
        return start;
    }

    public String getNum() {
        return num;
    }

    public AavaProtocolType setNum(String num) {
        this.num = num;
        return this;
    }
}
