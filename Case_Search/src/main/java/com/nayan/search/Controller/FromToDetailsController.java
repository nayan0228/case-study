package com.nayan.search.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.search.Service.FromToDetailsService;
import com.nayan.search.model.flightDetails;

@RestController
public class FromToDetailsController {
	
	/* GuestUser */
	 @Autowired
	 FromToDetailsService Service;
	 //User entering departure , destination ,date
	 @GetMapping("/flightdetails/{dep1}/{des1}/{date}") 
	 public List<flightDetails> getAllflightDetails(@PathVariable String dep1,@PathVariable String des1,@PathVariable String date) {
		 
		return Service.getAllflightDetails(dep1, des1, date);
	}
	 
	 
	 //for microservice to retrieve price
	 @GetMapping("/flight/{id}")
		private flightDetails getflightDetails(@PathVariable String id) {
			return Service.getflightDetailsByName(id);
		}
	
	
}
