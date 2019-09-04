package com.future.iot.controller.web;

import com.future.iot.crypto.CryptoManage;
import com.future.iot.model.Device;
import com.future.iot.model.Employee;
import com.future.iot.model.KeyManageDevice;
import com.future.iot.model.Reley;
import com.future.iot.repo.DeviceRepository;
import com.future.iot.repo.EmployeeRepository;
import com.future.iot.repo.KeyManageDeviceRepository;
import com.future.iot.repo.ReleyRepository;
import com.future.iot.service.ReleyDetailService;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Date;

@Controller
@RequestMapping("/device/reley")
public class ReleyController {
    private static final Logger LOG = Logger.getLogger(ReleyController.class);

    @Autowired
    private Environment        env;

    @Autowired
    private ReleyDetailService releyDetailService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ReleyRepository    releyRepo;
    @Autowired
    private DeviceRepository   deviceRepo;
    @Autowired
    private MqttClient         mqttClient;
    @Autowired
    private KeyManageDeviceRepository keyManageDeviceRepo;
    @Autowired
    private CryptoManage       cryptoManage;


    @GetMapping({"/list", "/{deviceId}"})
    public ModelAndView getListReley(Principal principal){
        Employee emp = employeeRepository.getOne(principal.getName());

        ModelAndView mv = new ModelAndView("device/control.reley");
        mv.addObject("employee", emp);
        mv.addObject("lstReley", releyDetailService.getListReleyDetailDto(emp.getId()));
        return mv;
    }

    @MessageMapping("/device/reley/control/{macAddress}-{gpio}")
    public void controlReley(Reley reley, Principal principal,
                             @DestinationVariable String macAddress,
                             @DestinationVariable short gpio) {

        String username = deviceRepo.getUsernameById(reley.getDeviceId());
        if(username.equals(principal.getName())) {

            KeyManageDevice keyManageDevice = keyManageDeviceRepo.findById(macAddress).get();

            Reley releyStorage = releyRepo.findByDeviceId(reley.getDeviceId());
            if(releyStorage == null) {
                releyStorage = new Reley();
            }

            String data  = gpio + ";" + reley.getValue() + ";" + reley.getTimeLable();
            String msg   = cryptoManage.handlerDataBeforeSend(data, keyManageDevice);
            String topic = env.getProperty("mqtt.topic.reley.control")  + macAddress;
            Integer qos  = env.getProperty("mqtt.qos", Integer.class);
            try {
                MqttMessage message = new MqttMessage();
                message.setPayload(msg.getBytes());
                message.setQos(qos);
                mqttClient.publish(topic, message);
            }catch(MqttException e){
                LOG.info("Lỗi Máy Chủ MQTT !");
            }
            releyStorage.setTimeLable(reley.getTimeLable());
            releyStorage.setDeviceId(reley.getDeviceId());
            releyStorage.setValue(reley.getValue());
            releyStorage.setUpdateTime(new Date());
            releyRepo.save(releyStorage);
        }
    }
}
