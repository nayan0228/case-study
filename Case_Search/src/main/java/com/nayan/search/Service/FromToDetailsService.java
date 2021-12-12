package com.nayan.search.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nayan.search.model.flightDetails;

@Service
   public class FromToDetailsService {


	@Autowired
	com.nayan.search.repo.flightDetailsRepository flightDetailsRepository;
	
	
	//Mapping values from flight collection in database
	
	public  List<flightDetails> getAllflightDetails(String dep1,String des1,String date) {
		
		List<flightDetails> flights = (List<flightDetails>) new ArrayList<flightDetails>();
		List<flightDetails> Match = (List<flightDetails>) new ArrayList<flightDetails>();
		
		flightDetailsRepository.findAll().forEach(e1 -> flights.add(e1)); 
		
		
		  for(flightDetails f: flights) {
		  if(f.getDeparture().equals(dep1) && f.getDestination().equals(des1) && f.getDate().equals(date))
		  { 
			  Match.add(f);
		  
		  }
		  }
		 
		
		System.out.println(Match);
		return Match;
		
		 
	}


	//Add flight data
	public String save(flightDetails f1) {
		
		flightDetailsRepository.save(f1);	
		return ("Flight Added with ID: "+ f1.getFlightId());
	}
	


	
	
   //get all flights details
	
	public List<flightDetails> getflightDetails() {
		List<flightDetails> f2 = new ArrayList<flightDetails>();
		flightDetailsRepository.findAll().forEach(e1 -> f2.add(e1));
		return f2; 
		
			
		}

	//find flight details by id
	public flightDetails getflightDetailsByName(String id) {
		return flightDetailsRepository.findById(id).get(); 
	}


	//delete flight
	public String deleteflightDetails(String id) {
		flightDetailsRepository.deleteById(id);
		return ("Deleted flight id "+id);
		
	}


	//update flight
	public void saveOrUpdate(flightDetails data) {
		flightDetailsRepository.save(data);
		
	}
	

	

}
