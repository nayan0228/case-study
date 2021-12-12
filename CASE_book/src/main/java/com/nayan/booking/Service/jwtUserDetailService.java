package com.nayan.booking.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nayan.booking.repo.UserDetailsRepository;


@Service
public class jwtUserDetailService implements UserDetailsService{
	
	@Autowired
	UserDetailsRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.nayan.booking.model.UserDetail user= userRepo.findByUsername(username);
		System.out.println("Hello"+ user);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}