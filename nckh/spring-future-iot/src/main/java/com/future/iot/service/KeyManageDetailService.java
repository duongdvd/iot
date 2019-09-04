package com.future.iot.service;

import com.future.iot.dto.KeyManageDeviceDetailsDto;

import java.util.List;

public interface KeyManageDetailService {
    List<KeyManageDeviceDetailsDto> getListDeviceAndKeyDto(int empId);
}
