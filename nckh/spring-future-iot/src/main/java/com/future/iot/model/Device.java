package com.future.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "device", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Device {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;


    @Size(min = 1, max = 100)
    @Column(name = "mac_address")
    private String macAddress;

    @Column(name = "gpio")
    private short gpio;

    @Size(max = 30)
    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status = "Ngoại tuyến";

    @NotBlank
    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "employee_id")
    private int employeeId;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "type_code", referencedColumnName = "type_code", insertable = false, updatable = false)
    private TypeDevice typeDevice;


    public String getMacAddress() {
        return macAddress;
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


    public String getTypeCode() {
        return typeCode;
    }


    public int getEmployeeId(){
        return employeeId;
    }


    public TypeDevice getTypeDevice() {
        return typeDevice;
    }


    public void setTypeDevice(TypeDevice typeDevice) {
        this.typeDevice = typeDevice;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }


    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }




    public void setStatus(String status) {
        this.status = status;
    }

    public short getGpio() {
        return gpio;
    }

    public void setGpio(short gpio) {
        this.gpio = gpio;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

