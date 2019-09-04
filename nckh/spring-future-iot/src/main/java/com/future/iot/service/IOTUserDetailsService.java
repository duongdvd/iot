package com.future.iot.service;

import com.future.iot.model.Employee;
import com.future.iot.model.Role;
import com.future.iot.repo.EmployeeRepository;
import com.future.iot.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userDetailsService")
public class IOTUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository empRepo;
    @Autowired
    private RoleRepository     roleRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = empRepo.getOne(username);
        if (employee == null) {
            throw new UsernameNotFoundException("User not authorized.");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = roleRepo.getAllByEmployeeId(employee.getId());
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));

        return new org.springframework.security.core.userdetails.User(employee.getUsername(), employee.getHashPass(), true, true, true, true, authorities);
    }

}
