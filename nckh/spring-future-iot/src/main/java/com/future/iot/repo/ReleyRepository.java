package com.future.iot.repo;

import com.future.iot.model.Reley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ReleyRepository extends JpaRepository<Reley, Integer> {
    @Query(value = " SELECT * from reley WHERE device_id = ?", nativeQuery = true)
    Reley findByDeviceId(int deviceId);


}
