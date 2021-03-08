package com.iteaj.network.device.client.jql.call;

import com.alibaba.fastjson.JSONObject;
import com.iteaj.iot.client.http.HttpMethod;
import com.iteaj.iot.client.http.HttpRequestMessage;

/**
 * 获取设备信息
 */
public class JqlGetDeviceInfo extends JqlProtocolAbstract {

    private String deviceId;

    private Payload payload;

    public JqlGetDeviceInfo(String url, String deviceId) {
        super(url);
        this.deviceId = deviceId;
    }

    @Override
    protected void doResolverResponseMessage(JSONObject parse) {
        this.payload = parse.getJSONObject("payload").toJavaObject(Payload.class);
    }

    @Override
    public JqlCallProtocolType protocolType() {
        return JqlCallProtocolType.DeviceInfo;
    }

    @Override
    public String getUri() {
        return "/api/devices/"+this.deviceId+"/";
    }

    @Override
    protected void doBuildRequestMessage(HttpRequestMessage message) {

    }

    @Override
    protected HttpMethod getMethod() {
        return HttpMethod.Get;
    }

    public static class Payload {
        private String DevID; // 设备 id",
        private String DevIP; // "192.168.0.111",//设备
        private String DevMASK; //子网掩码
        private String DevGW; //网关
        private String Alias; // 别名
        private String SvrIP; //该设备配置指向的服务器 IP 地址
        private String DNS;
        private String MacAddr;
        private String SvrUpper;//上级呼叫地址
        private int priority;//优先级
        private int shortOut1def;//IO 短路输出定义
        private int shortOut2def;//IO 短路输出定义
        private int AudioPort;//音频端口
        private int VideoPort;//视频端口
        private int RecordOnOff;//录像设置，0 录像 1 不录像
        private String SetParam1;//扩展参数"
        private String SetParam2;//扩展参数"
        private String SetParam3;//扩展参数
        private String Version;//版本号

        public String getDevID() {
            return DevID;
        }

        public void setDevID(String devID) {
            DevID = devID;
        }

        public String getDevIP() {
            return DevIP;
        }

        public void setDevIP(String devIP) {
            DevIP = devIP;
        }

        public String getDevMASK() {
            return DevMASK;
        }

        public void setDevMASK(String devMASK) {
            DevMASK = devMASK;
        }

        public String getDevGW() {
            return DevGW;
        }

        public void setDevGW(String devGW) {
            DevGW = devGW;
        }

        public String getAlias() {
            return Alias;
        }

        public void setAlias(String alias) {
            Alias = alias;
        }

        public String getSvrIP() {
            return SvrIP;
        }

        public void setSvrIP(String svrIP) {
            SvrIP = svrIP;
        }

        public String getDNS() {
            return DNS;
        }

        public void setDNS(String DNS) {
            this.DNS = DNS;
        }

        public String getMacAddr() {
            return MacAddr;
        }

        public void setMacAddr(String macAddr) {
            MacAddr = macAddr;
        }

        public String getSvrUpper() {
            return SvrUpper;
        }

        public void setSvrUpper(String svrUpper) {
            SvrUpper = svrUpper;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public int getShortOut1def() {
            return shortOut1def;
        }

        public void setShortOut1def(int shortOut1def) {
            this.shortOut1def = shortOut1def;
        }

        public int getShortOut2def() {
            return shortOut2def;
        }

        public void setShortOut2def(int shortOut2def) {
            this.shortOut2def = shortOut2def;
        }

        public int getAudioPort() {
            return AudioPort;
        }

        public void setAudioPort(int audioPort) {
            AudioPort = audioPort;
        }

        public int getVideoPort() {
            return VideoPort;
        }

        public void setVideoPort(int videoPort) {
            VideoPort = videoPort;
        }

        public int getRecordOnOff() {
            return RecordOnOff;
        }

        public void setRecordOnOff(int recordOnOff) {
            RecordOnOff = recordOnOff;
        }

        public String getSetParam1() {
            return SetParam1;
        }

        public void setSetParam1(String setParam1) {
            SetParam1 = setParam1;
        }

        public String getSetParam2() {
            return SetParam2;
        }

        public void setSetParam2(String setParam2) {
            SetParam2 = setParam2;
        }

        public String getSetParam3() {
            return SetParam3;
        }

        public void setSetParam3(String setParam3) {
            SetParam3 = setParam3;
        }

        public String getVersion() {
            return Version;
        }

        public void setVersion(String version) {
            Version = version;
        }
    }

    public Payload getPayload() {
        return payload;
    }
}
