package com.iteaj.network.device.server.aava.protocol;

import com.iteaj.network.device.server.aava.AavaProtocolType;

/**
 * 新版声光报警器的开或关
 */
public class AavaNewStatusSwitch extends AavaStatusSwitch {

    // 闪光状态
    private AavaFlashStatus flashStatus;
    // 音量状态
    private AavaVolumeStatus volumeStatus;
    // 播放状态
    private AavaPlayStatus playStatus;
    // 曲目类型
    private AavaSongType songType;

    public AavaNewStatusSwitch(String equipCode, AavaFlashStatus flashStatus
            , AavaVolumeStatus volumeStatus, AavaPlayStatus playStatus, AavaSongType songType) {
        super(equipCode, getStatusSwitch(flashStatus, volumeStatus));
        this.flashStatus = flashStatus;
        this.volumeStatus = volumeStatus;
        this.playStatus = playStatus;
        this.songType = songType;
    }

    public AavaNewStatusSwitch(String equipCode, String address, AavaFlashStatus flashStatus
            , AavaVolumeStatus volumeStatus, AavaPlayStatus playStatus, AavaSongType songType) {
        super(equipCode, address, getStatusSwitch(flashStatus, volumeStatus));
        this.flashStatus = flashStatus;
        this.volumeStatus = volumeStatus;
        this.playStatus = playStatus;
        this.songType = songType;
    }

    private static StatusSwitch getStatusSwitch(AavaFlashStatus flashStatus, AavaVolumeStatus volumeStatus) {
        return flashStatus==AavaFlashStatus.关闭 && volumeStatus ==
                AavaVolumeStatus.close ? StatusSwitch.关闭 : null;
    }

    @Override
    protected String getData() {
        return flashStatus.getCode()+volumeStatus.getCode()
                +playStatus.getCode()+songType.getCode();
    }

    @Override
    public AavaProtocolType protocolType() {
        return AavaProtocolType.Switch.setNum("0004");
    }

    public AavaFlashStatus getFlashStatus() {
        return flashStatus;
    }

    public AavaVolumeStatus getVolumeStatus() {
        return volumeStatus;
    }

    public AavaPlayStatus getPlayStatus() {
        return playStatus;
    }

    public AavaSongType getSongType() {
        return songType;
    }
}
