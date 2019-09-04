
package com.future.iot.repo;

import com.future.iot.model.SensorTemperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.math.BigInteger;


public interface SensorTemperatureRepository extends JpaRepository<SensorTemperature, BigInteger> {
    @Query(value = "SELECT EXISTS(SELECT 1 FROM sensor_temperature  WHERE device_id = ? and time_lable = ? LIMIT 1)",
            nativeQuery = true)
    int result(int deviceId, int timeLable);
}
