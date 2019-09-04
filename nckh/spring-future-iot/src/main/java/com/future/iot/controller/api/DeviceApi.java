package com.future.iot.controller.api;


import com.future.iot.model.Device;
import com.future.iot.repo.DeviceRepository;
import com.future.iot.repo.KeyManageDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceApi {

    @Autowired
    private DeviceRepository          deviceRepo;
    @Autowired
    private KeyManageDeviceRepository keyManageDeviceRepo;


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") int id){
        Device device = deviceRepo.getOne(id);
        deviceRepo.delete(device);
        if(deviceRepo.result(device.getMacAddress()) == 0) {
            keyManageDeviceRepo.deleteById(device.getMacAddress());
        }
        return ResponseEntity.ok().build();
    }

}
