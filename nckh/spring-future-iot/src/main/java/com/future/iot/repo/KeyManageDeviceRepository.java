package com.future.iot.repo;

import com.future.iot.model.KeyManageDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface KeyManageDeviceRepository extends JpaRepository<KeyManageDevice, String> {


}
