package com.future.iot.repo;

import com.future.iot.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM device  WHERE mac_address = ? and gpio = ? LIMIT 1)",
            nativeQuery = true)
    int result(String mac_address, short gpio);

    @Query(value = "SELECT EXISTS(SELECT 1 FROM device  WHERE mac_address = ? LIMIT 1)",
            nativeQuery = true)
    int result(String mac_address);

    @Query(value = "SELECT * FROM device  WHERE mac_address = ? AND gpio = ?", nativeQuery = true)
    Device getOne(String macAddress, short gpio);

    @Query(value = "SELECT e.username FROM device d JOIN employee e ON d.employee_id = e.id WHERE d.id = ?",
            nativeQuery = true)
    String getUsernameById(int id);

    @Query(value = "SELECT * FROM device  WHERE id = ?", nativeQuery = true)
    Device getOne(int id);

}
