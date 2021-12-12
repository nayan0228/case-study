package com.nayan.booking.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nayan.booking.model.SeatAvaliability;
import com.nayan.booking.model.UserDetail;
import com.nayan.booking.repo.UserDetailsRepository;
import com.nayan.booking.repo.seatAvalibilityRepo;


@Service
public class AvaliabilityService {
	
	@Autowired
	seatAvalibilityRepo seatRepository;
	@Autowired
	UserDetailsRepository userRepository;
	
	//add vacancy list
	public void save(SeatAvaliability seat) {
		seatRepository.save(seat);	
	}
	
	//particular flights passenger list
	public List<UserDetail> getFlightUser(String id) {
		
		List<UserDetail> user = new ArrayList<UserDetail>();
		 List<UserDetail> Match = new ArrayList<UserDetail>();		
		userRepository.findAll().forEach(e1 -> user.add(e1));
		 System.out.println(user);	
		
		  for(UserDetail f: user)
		  { 
			  System.out.println(f.getFlightId()+" "+id);	
			  if( f.getFlightId().equals(id)) { 
			  Match.add(f);
		  
		  } 
		  }
		  System.out.println(Match);
		  return Match;
		  
		 
	
	/*
	 * public void saveOrUpdate(SeatAvaliability data) { seatRepository.save(data);
	 * 
	 * }
	 */
	
}
}
