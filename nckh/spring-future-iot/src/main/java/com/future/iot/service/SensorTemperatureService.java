package com.future.iot.service;

import com.future.iot.dto.SensorTemperatureDto;

import java.util.List;


public interface SensorTemperatureService {
    SensorTemperatureDto getOne(int deviceId, int empId);
    List<SensorTemperatureDto> getListTemperatureDto(int empId);
}
