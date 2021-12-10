package com.nayan.booking.repo;

import org.springframework.data.repository.CrudRepository;

import com.nayan.booking.model.UserDetail;



public interface UserDetailsRepository extends CrudRepository<UserDetail, String>  
{



	UserDetail findByUsername(String username);  
}