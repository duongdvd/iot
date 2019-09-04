package com.future.iot.impl;

import com.future.iot.dto.SensorTemperatureDto;
import com.future.iot.service.SensorTemperatureService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SensorTemperatureServiceImpl implements SensorTemperatureService {

    @PersistenceContext
    private EntityManager em;

    private SensorTemperatureDto mapToSensorTemperatureDto(Object[] objs) {
        SensorTemperatureDto dto = new SensorTemperatureDto();
        dto.setDeviceId((int) objs[0]);
        dto.setDescription(objs[1].toString());
        dto.setStatus((String) Optional.ofNullable(objs[2]).orElse("UnKnow"));
        dto.setTemperaturevalue((float) Optional.ofNullable(objs[3]).orElse(0f));
        dto.setHumidityValue((float) Optional.ofNullable(objs[4]).orElse(0f));
        dto.setUpdateTime((String) objs[5]);
        return dto;
    }

    @Override
    public SensorTemperatureDto getOne(int deviceId, int empId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  d.id, d.description, d.status, s.temperature_value, ");
        sql.append("s.humidity_value, date_format(s.update_time,'%r %d-%m-%y') ");
        sql.append("FROM device d LEFT JOIN sensor_temperature s ON d.id = s.device_id ");
        sql.append("WHERE d.type_code = 'cbnd' AND d.id = ? AND d.employee_id = ? ");
        sql.append("AND (s.id = (select max(id) from sensor_temperature where device_id = d.id) ");
        sql.append("OR s.id IS NULL)");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, deviceId);
        query.setParameter(2, empId);
        Optional<Object[]> objs =  query.getResultStream().findFirst();
        return objs.isPresent() ? this.mapToSensorTemperatureDto(objs.get()) : null;
    }

    @Override
    public List<SensorTemperatureDto> getListTemperatureDto(int empId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  d.id, d.description, d.status, s.temperature_value, ");
        sql.append("s.humidity_value, date_format(s.update_time,'%r %d-%m-%y') ");
        sql.append("FROM device d LEFT JOIN sensor_temperature s ON d.id = s.device_id ");
        sql.append("WHERE d.type_code = 'cbnd' AND d.employee_id = ? ");
        sql.append("AND (s.id = (select max(id) from sensor_temperature where device_id = d.id) ");
        sql.append("OR s.id IS NULL)");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, empId);
        List<Object[]> lstObjs = query.getResultList();
        return lstObjs.stream().map(this::mapToSensorTemperatureDto).collect(Collectors.toList());
    }
}
