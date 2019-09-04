package com.future.iot.impl;

import com.future.iot.dto.KeyManageDeviceDetailsDto;
import com.future.iot.service.KeyManageDetailService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KeyManageDetailServiceImpl implements KeyManageDetailService {

    @PersistenceContext
    private EntityManager em;

    private KeyManageDeviceDetailsDto mapToDeviceAndKeyDto(Object[] objs){
        KeyManageDeviceDetailsDto dto = new KeyManageDeviceDetailsDto();
        dto.setDeviceId((int) objs[0]);
        dto.setDescription(objs[1].toString());
        dto.setKeySecret((String) Optional.ofNullable(objs[2]).orElse("none"));
        dto.setInitVector((String) Optional.ofNullable(objs[3]).orElse("none"));
        dto.setAlgorithm((String) Optional.ofNullable(objs[4]).orElse("none"));
        dto.setTypeCode(objs[5].toString());
        dto.setTypeName(objs[6].toString());
        return dto;
    }

    @Override
    public List<KeyManageDeviceDetailsDto> getListDeviceAndKeyDto(int empId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT  d.id, d.description, k.secret_key, ");
        sql.append("k.iv, k.algo, d.type_code, t.type_name  ");
        sql.append("FROM device d LEFT JOIN type_device t ON d.type_code = t.type_code ");
        sql.append("LEFT JOIN  key_manage_device k ON d.mac_address = k.mac_address ");
        sql.append("WHERE d.employee_id = ? ");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, empId);
        List<Object[]> lstObjs = query.getResultList();
        return lstObjs.stream().map(this::mapToDeviceAndKeyDto).collect(Collectors.toList());
    }
}
