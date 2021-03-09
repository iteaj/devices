package com.iteaj.network.device.server;

import com.iteaj.network.config.DeviceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "iot.device.server")
public class DeviceServerProperties {

    /**
     * 智慧融合控制台设备配置
     */
    private ServerConfig pdu;

    /**
     * 空调温湿度烟雾设备配置
     */
    private ServerConfig ths;

    /**
     * 多功能环境监测设备
     */
    private ServerConfig env;

    /**
     * 7合一多功能环境监测设备配置
     */
    private ServerConfig m702;

    /**
     * Gps定位设备 <h4>道路运输车辆主动安全智能防控系统</h4>
     */
    private ServerConfig gps;

    /**
     * 声光报警器
     */
    private ServerConfig aava;

    /**
     * 氨气
     */
    private ServerConfig nh3;

    public ServerConfig getPdu() {
        return pdu;
    }

    public void setPdu(ServerConfig pdu) {
        this.pdu = pdu;
    }

    public ServerConfig getThs() {
        return ths;
    }

    public void setThs(ServerConfig ths) {
        this.ths = ths;
    }

    public ServerConfig getEnv() {
        return env;
    }

    public void setEnv(ServerConfig env) {
        this.env = env;
    }

    public ServerConfig getM702() {
        return m702;
    }

    public void setM702(ServerConfig m702) {
        this.m702 = m702;
    }

    public ServerConfig getGps() {
        return gps;
    }

    public void setGps(ServerConfig gps) {
        this.gps = gps;
    }

    public ServerConfig getAava() {
        return aava;
    }

    public void setAava(ServerConfig aava) {
        this.aava = aava;
    }

    public ServerConfig getNh3() {
        return nh3;
    }

    public void setNh3(ServerConfig nh3) {
        this.nh3 = nh3;
    }

    public static class ServerConfig extends DeviceProperties {

    }
}
