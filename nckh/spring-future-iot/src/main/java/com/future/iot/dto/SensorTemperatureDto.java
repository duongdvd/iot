package com.future.iot.dto;


public class SensorTemperatureDto {

    private int    deviceId;
    private String description;
    private String status;
    private String typeCode;
    private int    employeeId;
    private float  temperatureValue;
    private float  humidityValue;
    private String updateTime;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public void setTemperatureValue(float temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public float getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperaturevalue(float temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public float getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(float humidityValue) {
        this.humidityValue = humidityValue;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
