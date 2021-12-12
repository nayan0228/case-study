package com.nayan.booking.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nayan.booking.model.SeatAvaliability;
import com.nayan.booking.model.UserDetail;
import com.nayan.booking.repo.UserDetailsRepository;
import com.nayan.booking.repo.seatAvalibilityRepo;

@Service
public class UserServices {

	@Autowired
	UserDetailsRepository userRepository;
	@Autowired
	seatAvalibilityRepo seat1Repository;

	// Adding data to user collection
	 public String save(UserDetail u) {
		System.out.println(u.getNoOfPassengers());
		SeatAvaliability flightseat = seat1Repository.findById(u.getFlightId()).get(); 
		System.out.println(flightseat);
		System.out.println(flightseat.getAvaliable());
		
		if(u.getNoOfPassengers() <= flightseat.getAvaliable() ) {
					      userRepository.save(u);
		
			int updatedseats = flightseat.getAvaliable()- u.getNoOfPassengers();
			flightseat.avaliable=updatedseats;
			
			
			seat1Repository.save(flightseat);
			return ("flightId: "+ u.getFlightId()+" BookingId: "+ u.getBookingid()+" Seats Booked:" + u.getNoOfPassengers());
			  
			  }
		else {
			return "Seats Not Avaliable";
		}
			
			 
		}

	 //User details of particular user by id
			public UserDetail getUserDetailsByName(String id) {
				
				return userRepository.findById(id).get();
			}

			public Object addUserDetail(UserDetail user) {
				// TODO Auto-generated method stub
				return null;
			}
			
			
			
			
	

}
