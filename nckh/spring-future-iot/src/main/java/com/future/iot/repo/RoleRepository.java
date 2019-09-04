package com.future.iot.repo;

import com.future.iot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "SELECT * FROM role WHERE employee_id = ?", nativeQuery = true)
    List<Role> getAllByEmployeeId(int empId);
}
