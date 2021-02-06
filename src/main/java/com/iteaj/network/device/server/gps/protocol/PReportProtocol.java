package com.iteaj.network.device.server.gps.protocol;

import com.iteaj.network.device.server.gps.GpsCommonRespResult;
import com.iteaj.network.device.server.gps.GpsMessage;
import com.iteaj.network.device.server.gps.GpsProtocolType;
import com.iteaj.network.utils.ByteUtil;

/**
 * create time: 2021/1/22
 *  位置信息上报协议
 * @author iteaj
 * @since 1.0
 */
public class PReportProtocol extends GpsDeviceRequestProtocol {

    /**
     * 报警标志位
     */
    private String warningFlag;

    /**
     * 状态
     */
    private String status;

    /**
     * 以度为单位的纬度值乘以10的6次方，精确到百万分之一度
     */
    private int lat;

    /**
     * 以度为单位的纬度值乘以10的6次方，精确到百万分之一度
     */
    private int lon;

    /**
     * 海拔高度，单位为米(m)
     */
    private int height;


    /**
     * 1/10km/h
     */
    private int speed;

    /**
     * 方向 0-359,正北为0，顺时针
     */
    private int dire;

    /**
     * YY-MM-DD-hh-mm-ss(GMT+8时间)
     */
    private String dateTime;

    /**
     * 未知信息上报结果
     */
    private GpsCommonRespResult result = GpsCommonRespResult.成功;

    private static final String EmptyBinary = "00000000000000000000000000000000";

    public PReportProtocol(GpsMessage requestMessage) {
        super(requestMessage);
    }

    @Override
    protected GpsMessage doBuildResponseMessage() {
        return GpsMessage.buildPlatformCommonRespMessageByRequest(requestMessage(), result);
    }

    @Override
    protected void resolverRequestMessage(GpsMessage requestMessage) {
        byte[] bodyMessage = requestMessage.getBody().getBodyMessage();
        System.out.println("Gps经纬度协议体：" + ByteUtil.bytesToHex(bodyMessage));

        int bytesToInt = ByteUtil.bytesToInt(bodyMessage, 0);
        this.warningFlag = getIntBinary(bytesToInt);

        this.status = getIntBinary(ByteUtil.bytesToInt(bodyMessage, 4));

        this.lat = ByteUtil.bytesToIntOfNegate(bodyMessage, 8);

        this.lon = ByteUtil.bytesToIntOfNegate(bodyMessage, 12);

        this.height = ByteUtil.bytesToShortOfNegate(bodyMessage, 16);

        this.speed = ByteUtil.bytesToShortOfNegate(bodyMessage, 18);

        this.dire = ByteUtil.bytesToShortOfNegate(bodyMessage, 20);

        this.dateTime = ByteUtil.bcdToStr(bodyMessage, 22, 6);
    }

    @Override
    public GpsProtocolType protocolType() {
        return GpsProtocolType.PReport;
    }

    protected String getIntBinary(int val) {
        String binaryString = Integer.toBinaryString(val);
        if(binaryString.length() == 32) return binaryString;

        String substring = EmptyBinary.substring(0, 32 - binaryString.length());

        return substring+binaryString;
    }

    @Override
    public String toString() {
        return "PReportProtocol{" +
                "warningFlag='" + warningFlag + '\'' +
                ", status='" + status + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", height=" + height +
                ", speed=" + speed +
                ", dire=" + dire +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    /**
     * 0 0：ACC 关 1：ACC 开
     * 1 0：未定位 1：定位
     * 2 0：北纬 1：南纬
     * 3 0：东经 1：西经
     * 4 0：运营状态 1：停运状态
     * 5 0：经纬度未经保密插件加密；1：经纬度已经保密插件加密
     * 6 单北斗 单GPS 双模 自定义协义
     *      1    0    1
     * 7    0    1    1
     * 8-9 00:空车; 01:半载; 10:保留 ; 11:满载
     * 10 0：车辆油路正常 1：车辆油路断开
     * 11 0：车辆电路正常 1：车辆电路断开
     * 12 0：车门解锁 1：车门加锁
     *
     *以下是厂家自定义的状态位或新北斗协义的状态说明, 有冲突的状态位会标注出来
     * 13 0：前门关 1：前门开 （门磁线） 自定义协义
     *    0: 门 1 关; 1:门 1 开(前门) 新北斗协义 √
     * 14 0：后门关 或自定义高 2 无效 1：后门开或自定义高 2 有效 自定义协义
     *    0: 门 2 关; 1:门 2 开(中门) 新北斗协义 √
     * 15 0：发动机关 1：发动机开 自定义协义
     *    0: 门 3 关; 1:门 3 开(后门) 新北斗协义
     * 16 0：空调关 1：空调开 自定义协义
     *    0: 门 4 关; 1:门 4 开(驾驶度门) 新北斗协义
     * 17 0：未刹车 1：刹车 自定义协义
     *    0: 门 5 关; 1:门 5 开(自定义) 新北斗协义
     * 18 0：左转向关 1：左转向开 自定义协义
     *    0: 未使用 GPS 卫星进行定位; 1:使用 GPS 卫星进行定位 新北斗协义 √
     * 19 0：右转向关 1：右转向开 自定义协义
     *    0: 未使用北斗卫星进行定位;1:使用北斗卫星进行定位 新北斗协义 √
     * 20 0：远光关 1：远光开 自定义协义
     *    0: 未使用 GLONASS 卫星进行定位;1:使用 GLONASS 卫星进行定位 新北斗协义
     * 21 0：近光关 或自定义高 3 无效 1：近光开或自定义高 3 有效改为正反转检测 1= 转或 0=不转[正反转用振动检测线对接]
     *    0: 未使用 Galileo 卫星进行定位;1:使用 Galileo 卫星进行定位 新北斗协义
     * 22 0：震动关 1：震动开
     * 23 0：喇叭关 1：喇叭开
     * 24 0：自定义高 1 无效 1：自定义高 1 有效
     * 25 0：自定义输入低 1 无效或转鼓 1 无效 ，1：自定义输入低 1 有效或转鼓 1有效
     * 26 0：自定义输入低 2 无效或转鼓 2 无效，1：自定义输入低 2 有效或转鼓 2 有效 正反转检测时, 0=正转 , 1=反转, [正反转用振动检测线对接]
     * 27 0: 自定义输出高 1 无效，1：自定义输出高 1 有效
     * 28 0：自定义输出低 1 无效或外置蜂鸣器无效，1：自定义输出低 1 有效或外置蜂鸣器 1 有效
     * 29 0：解防状态 1：设防状态
     *      注:G9 终端做为对讲机动作状态 ,0 :无对讲 , 1:对讲状态
     * 30 0:空载状态 1:重载状态
     *      注：3G 视频终端做为 SIM 卡异常状态，0 正常，1 异常
     * 31 保留
     * @param offset
     * @return
     */
    public int getStatus(int offset) {
        if(offset<0 || offset>31) {
            throw new IllegalArgumentException("偏移位数必须在[0, 31]");
        }

        char charAt = this.getStatus().charAt(offset);
        return charAt == '1' ? 1 : 0;
    }

    /**
     * 0 1：紧急报警，触动报警开关后触发 收到应答后清零
     *
     * 1 1：超速报警 标志维持至报警条件解除
     *
     * 2 1: 疲劳驾驶 标志维持至报警条件解除
     *
     * 3 1：预警 收到应答后清零
     *
     * 4 1：GNSS 模块发生故障 标志维持至报警条件解除
     *
     * 5 1：GNSS 天线未接或被剪断 标志维持至报警条件解除
     *
     * 6 1：GNSS 天线短路 标志维持至报警条件解除
     *
     * 7 1：终端主电源欠压 标志维持至报警条件解除
     *
     * 8 1：终端主电源掉电 标志维持至报警条件解除
     *
     * 9 1：终端 LCD 或显示器故障 标志维持至报警条件解除
     *
     * 10 1：TTS 模块故障 标志维持至报警条件解除
     *
     * 11 1：摄像头故障 标志维持至报警条件解除
     *
     * 12 1:道路运输证 IC 卡模块故障 标志维持至报警条件解除 新北斗协义
     *
     * 13 1:超速预警 标志维持至报警条件解除 新北斗协义
     *
     * 14 1:疲劳驾驶预警 标志维持至报警条件解除 新北斗协义
     *
     * 15 终端主电源高压 标志维持至报警条件解除 , 自定义
     *
     * 16 视频丢失报警(G9 终端) 标志维持至报警条件解除,带有附加信息 EB, 自定义
     *
     * 17 视频遮挡报警(G9 终端) 标志维持至报警条件解除, 带有附加信息 EB, 自定义
     *
     * 18 1：当天累计驾驶超时 标志维持至报警条件解除
     *
     * 19 1：超时停车 标志维持至报警条件解除
     *
     * 20 1：进出区域 收到应答后清零
     *
     * 21 1：进出路线 收到应答后清零
     *
     * 22 1：路段行驶时间不足/过长 收到应答后清零
     *
     * 23 １：路线偏离报警 标志维持至报警条件解除
     *
     * 24 1：车辆 VSS 故障 标志维持至报警条件解除
     *
     * 25 1：车辆油量异常 标志维持至报警条件解除
     *
     * 26 1：车辆被盗(通过车辆防盗器) 标志维持至报警条件解除
     *
     * 27 1：车辆非法点火 收到应答后清零
     *
     * 28 1：车辆非法位移 收到应答后清零
     *
     * 29 1：碰撞侧翻报警 普通部标协义
     *    1：碰撞预警 新北斗协义 标志维持至报警条件解除
     *
     * 30 1：SD 卡异常 G9 终端表示 SD 卡锁自定义
     *    1:侧翻预警 新北斗协义 标志维持至异常条件解除,
     *    注:自定义 与新北斗协义 只能选一
     *
     * 31 1:非法开门报警(终端未设置区域时,不判断非法开门) 收到应答后清零 新北斗协义
     * @param offset
     * @return
     */
    public int getWarningFlag(int offset) {
        if(offset<0 || offset>31) {
            throw new IllegalArgumentException("偏移位数必须在[0, 31]");
        }

        char charAt = this.getWarningFlag().charAt(offset);
        return charAt == '1' ? 1 : 0;
    }

    public GpsCommonRespResult getResult() {
        return result;
    }

    public PReportProtocol setResult(GpsCommonRespResult result) {
        this.result = result;
        return this;
    }

    public String getWarningFlag() {
        return warningFlag;
    }

    public String getStatus() {
        return status;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDire() {
        return dire;
    }

    public String getDateTime() {
        return dateTime;
    }
}
