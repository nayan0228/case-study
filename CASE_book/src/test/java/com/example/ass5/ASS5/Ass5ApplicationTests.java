package com.example.ass5.ASS5;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nayan.booking.model.UserDetail;
import com.nayan.booking.repo.UserDetailsRepository;

@SpringBootTest
class Ass5ApplicationTests {

@Autowired
private com.nayan.booking.Service.UserServices UserServices;

@MockBean
private UserDetailsRepository repo;

@Test
public  void saveAss5ApplicationTests() {
	UserDetail user= new UserDetail("100", "abc","abc1", "Pass123", 4,true);
	when(repo.save(user)).thenReturn(user);
	assertEquals(user, UserServices.addUserDetail(user));
}
			
	}


