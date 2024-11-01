package com.digitalojt.web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digitalojt.web.entity.AdminInfo;
import com.digitalojt.web.entity.UserDetailsImpl;
import com.digitalojt.web.repository.AdminInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	 private final AdminInfoRepository adminInfoRepository;

	    public UserDetailsServiceImpl(AdminInfoRepository adminInfoRepository) {
	        this.adminInfoRepository = adminInfoRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        AdminInfo adminInfo = adminInfoRepository.findByAdminId(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	        return new UserDetailsImpl(adminInfo.getAdminId(), adminInfo.getPassword());
	    }

}
