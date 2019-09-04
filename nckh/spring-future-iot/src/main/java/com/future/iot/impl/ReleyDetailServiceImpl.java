package com.future.iot.impl;

import com.future.iot.dto.ReleyDetailDto;
import com.future.iot.service.ReleyDetailService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReleyDetailServiceImpl implements ReleyDetailService {

    @PersistenceContext
    private EntityManager em;

    private ReleyDetailDto mapToReleyDetailDto(Object[] objs){
        ReleyDetailDto dto = new ReleyDetailDto();

        dto.setMacAddress(objs[0].toString());
        dto.setGpio(Short.parseShort(objs[1].toString()));
        dto.setDeviceId((int) objs[2]);
        dto.setDescription(objs[3].toString());
        dto.setValue((short) Optional.ofNullable(objs[4]).orElse((short) 0));
        dto.setUpdateTime((String) Optional.ofNullable(objs[5]).orElse("Chưa cập nhập !"));
        return dto;
    }

    @Override
    public List<ReleyDetailDto> getListReleyDetailDto(int empId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT d.mac_address, d.gpio, d.id, d.description, r.value, date_format(r.update_time,'%r %d-%m-%y') ");
        sql.append("FROM device d LEFT JOIN reley r ON  d.id = r.device_id ");
        sql.append("WHERE  d.type_code = 'reley'  AND d.employee_id = ? ");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, empId);
        List<Object[]> lstObjects = query.getResultList();
        return lstObjects.stream().map(this::mapToReleyDetailDto).collect(Collectors.toList());
    }
}
