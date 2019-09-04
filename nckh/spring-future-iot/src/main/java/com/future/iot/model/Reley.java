package com.future.iot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "reley", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "device_id"})})
public class Reley {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @JsonProperty
    private int id;

    @Column(name = "device_id")
    @JsonProperty
    private int deviceId;

    @Column(name = "value")
    @JsonProperty
    private short value;

    @Column(name = "update_time")
    @JsonProperty
    private Date updateTime;

    @JsonProperty
    @Column(name = "time_lable")
    private int timeLable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getTimeLable() {
        return timeLable++;
    }

    public void setTimeLable(int timeLable) {
        this.timeLable = timeLable;
    }
}
