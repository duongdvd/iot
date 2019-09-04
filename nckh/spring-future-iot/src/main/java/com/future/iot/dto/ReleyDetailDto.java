package com.future.iot.dto;


public class ReleyDetailDto {

    private String macAddress;

    private short gpio;

    private int deviceId;

    private String description;

    private String updateTime;

    private short value;

    public short getGpio() {
        return gpio;
    }

    public void setGpio(short gpio) {
        this.gpio = gpio;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
