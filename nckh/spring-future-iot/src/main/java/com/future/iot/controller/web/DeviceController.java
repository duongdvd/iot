package com.future.iot.controller.web;

import com.future.iot.crypto.CryptoManage;
import com.future.iot.model.Device;
import com.future.iot.model.Employee;
import com.future.iot.model.KeyManageDevice;
import com.future.iot.model.TypeDevice;
import com.future.iot.repo.DeviceRepository;
import com.future.iot.repo.EmployeeRepository;
import com.future.iot.repo.KeyManageDeviceRepository;
import com.future.iot.repo.TypeDeviceRepository;
import com.future.iot.service.KeyManageDetailService;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/device")
@PropertySource("classpath:/common.properties")
public class DeviceController {
    @Autowired
    private Environment               env;
    @Autowired
    private KeyManageDetailService    keyManageDetailService;
    @Autowired
    private EmployeeRepository        employeeRepository;
    @Autowired
    private TypeDeviceRepository      typeDeviceRepository;
    @Autowired
    private DeviceRepository          deviceRepository;
    @Autowired
    private CryptoManage              cryptoManage;
    @Autowired
    private KeyManageDeviceRepository keyManageDeviceRepo;


    @GetMapping("/add")
    public ModelAndView addDevice(Principal principal){
        Employee emp = employeeRepository.getOne(principal.getName());
        ModelAndView mv = new ModelAndView("device/device.add");
        mv.addObject("employee", emp);
        mv.addObject("device", new Device());
        mv.addObject("lstDeviceAndKey", keyManageDetailService.getListDeviceAndKeyDto(emp.getId()));
        mv.addObject("algorithms",getListAlgorithm());
        mv.addObject("optionTypeDevice", mapToTypeDevice(typeDeviceRepository.findAll()));
        mv.addObject("mapGpio", mapToGpio(env.getProperty("device.gpio.max", Integer.class)));
        return mv;
    }

    private List<String> getListAlgorithm(){
        String[] arrays = env.getProperty("arrays.name.cypto", String[].class);
        return Arrays.asList(arrays);
    }

    private Map<Short, String> mapToGpio(int maxGpio){
        Map<Short, String> map = new HashMap<>();
        IntStream.range(0, maxGpio + 1).forEach(i -> map.put((short) i, "GPIO" + i));
        return map;
    }

    private Map<String, String> mapToTypeDevice(List<TypeDevice> list){
        Map<String, String> map = new HashMap<>();
        list.forEach(t -> map.put(t.getTypeCode(), t.getTypeName()));
        return map;
    }

    @PostMapping("/save")
    public ModelAndView saveDevice(Principal principal,
                                   @Valid @ModelAttribute("device") Device device,
				   BindingResult result,
                                   @RequestParam(name = "algorithm") String algorithm){

        Employee emp = employeeRepository.getOne(principal.getName());
        ModelAndView mv = addDevice(principal);
        if(result.hasErrors()){
            mv.addObject("device", device);
	    mv.addObject("errors", result);
            return mv;
        }
        if (deviceRepository.result(device.getMacAddress(), device.getGpio()) == 1) {
            mv.addObject("device", device);
            mv.addObject("isExist", "Đã tồn tại!");
            return mv;
        }
        if(!keyManageDeviceRepo.existsById(device.getMacAddress())) {
            cryptoManage.getIntanceKey(algorithm);
            KeyManageDevice keyManageDevice = new KeyManageDevice();
            keyManageDevice.setAlgorithm(algorithm);
            keyManageDevice.setMacAddress(device.getMacAddress());
            keyManageDevice.setSecretKey(cryptoManage.getSecretKey());
            keyManageDevice.setIv(cryptoManage.getIv());
            keyManageDevice.setPublicKey(cryptoManage.getPublicKey());
            keyManageDevice.setPrivateKey(cryptoManage.getPrivateKey());
            keyManageDeviceRepo.save(keyManageDevice);
        }
        device.setEmployeeId(emp.getId());
        deviceRepository.save(device);
        mv = addDevice(principal);
        return mv;
    }



}
