package com.future.iot.service;

import com.future.iot.model.Employee;
import com.future.iot.model.Role;
import com.future.iot.repo.EmployeeRepository;
import com.future.iot.repo.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("userAuthPro")
public class UserAuthProvider implements AuthenticationProvider{
	private static final Logger   LOG = Logger.getLogger(UserAuthProvider.class);
	@Autowired
	private EmployeeRepository empRepo;
	@Autowired
	private RoleRepository     roleRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		Employee employee = empRepo.getOne(username);
		if(employee == null) return null;
		LOG.info("--------------------- > Found " + employee + " by " + username +"<-----------------------");
		if(!employee.getHashPass().equals(authentication.getCredentials())) return null;
		List<Role> roles = roleRepo.getAllByEmployeeId(employee.getId());
		return successful(username, authentication.getCredentials().toString(), roles);
	}

	private Authentication successful(String username, String password, List<Role> roles) {
		List<GrantedAuthority> grantedAuths = new ArrayList<>();
		roles.forEach(role -> grantedAuths.add(new SimpleGrantedAuthority(role.getRole())));
		return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}
