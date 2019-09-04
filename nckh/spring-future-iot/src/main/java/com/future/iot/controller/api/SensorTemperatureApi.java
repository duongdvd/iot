package com.future.iot.controller.api;

import com.future.iot.crypto.CryptoManage;
import com.future.iot.dto.SensorTemperatureDto;
import com.future.iot.model.Device;
import com.future.iot.model.Employee;
import com.future.iot.model.SensorTemperature;
import com.future.iot.repo.DeviceRepository;
import com.future.iot.repo.EmployeeRepository;
import com.future.iot.repo.SensorTemperatureRepository;
import com.future.iot.service.SensorTemperatureService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;


@RestController
@RequestMapping("/api/cbnd")
public class SensorTemperatureApi {

    private static final Logger LOG = Logger.getLogger(SensorTemperatureApi.class);
    @Autowired
    private SensorTemperatureRepository  tempRepo;
    @Autowired
    private DeviceRepository             deviceRepo;
    @Autowired
    private CryptoManage                 cryptoManage;
    @Autowired
    private SensorTemperatureService     temperatureService;
    @Autowired
    private EmployeeRepository           employeeRepository;



    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SensorTemperatureApi(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    public void handlerEnCryptoData(String data) { //mac+cipher
        String plainText = cryptoManage.handlerDataAfterReceive(data);//tempe-humi

        if(plainText.isEmpty()) return;
        try {
            String strs[] = plainText.split(";");
            String macAdrress       = strs[0];
            short gpio              = Short.parseShort(strs[1]);
            float  temperatureValue = Float.parseFloat(strs[2]) ;
            float  humidityValue    = Float.parseFloat(strs[3]);
            int    timeLable        = Integer.parseInt(strs[4]);

            Device device = deviceRepo.getOne(macAdrress, gpio);

            SensorTemperature sensor = new SensorTemperature();
            sensor.setDeviceId(device.getId());
            sensor.setTemperatureValue(temperatureValue);
            sensor.setHumidityValue(humidityValue);
            sensor.setTimeLable(timeLable);

            if(tempRepo.result(device.getId(), timeLable) == 0) {
                saveSensorTemperature(sensor);
            }else{
                LOG.info("Cảnh báo: Có dữ liệu phát lại!");
            }

        } catch (Exception e) {
            LOG.error(e.getMessage());
        }
    }


    @PostMapping("/save")
    public void saveSensorTemperature(@RequestBody SensorTemperature sensor) {
        Integer deviceId = sensor.getDeviceId();
        if(deviceId == null) return;
        sensor.setUpdateTime(new Date());
        tempRepo.save(sensor);
        int empId       = deviceRepo.findById(deviceId).get().getEmployeeId();
        String username = employeeRepository.findById(empId).get().getUsername();
        messagingTemplate.convertAndSend("/topic/cbnd/" + empId + username, sensor);

    }

    @SendTo("/topic/cbnd/{empId}{username}")
    public SensorTemperature getSensorOfWebSocket(SensorTemperature sensor) {
        return sensor;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<SensorTemperatureDto> getById(@PathVariable("id") int id, Principal principal) {
        Employee employee = employeeRepository.getOne(principal.getName());
        SensorTemperatureDto dto = temperatureService.getOne(id, employee.getId());
        return ResponseEntity.ok(dto);
    }



}
