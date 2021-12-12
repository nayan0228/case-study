package com.nayan.search.Controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.nayan.search.Service.FromToDetailsService;
import com.nayan.search.model.flightDetails;

@RestController
@RequestMapping("/admin") 
public class AdminController {
	
	 @Autowired
	 FromToDetailsService Service;
	 
	 //Add new flight details
	 
	  @PostMapping("/addflight") 
	  public String saveflightDetails(@RequestBody flightDetails e) 
	  { 
		  return Service.save(e);
		  
	  }
	  
	 @GetMapping("/ad")
	 public String admin() {
		 return "Hello admin";
	 }
	 
	 
        //flights avaliable currently
	 
	    @GetMapping("/flightsavaliable")
		private List<flightDetails> getAllflightDetails() {
			return Service.getflightDetails();
		}
		
		//Particular flight data
		
		@GetMapping("/flight/{id}")
		private flightDetails getflightDetails(@PathVariable String id) {
			return Service.getflightDetailsByName(id);
		}
		
		

		@DeleteMapping("/deleteflight/{id}")
		private String deleteflightDetails(@PathVariable String id) {
			return Service.deleteflightDetails(id);
		
		}
		
		@PutMapping("/updateflight")  
		private flightDetails update(@RequestBody flightDetails data)   
		{  
		Service.saveOrUpdate(data);  
		return data;  
		}

}
