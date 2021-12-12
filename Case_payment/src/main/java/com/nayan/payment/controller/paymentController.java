package com.nayan.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.payment.model.payment;
import com.nayan.payment.services.paymentService;

@RestController
@RequestMapping("/payment")
public class paymentController {
	
	@Autowired
	paymentService service;
	
	//check payment status
	@GetMapping("/checkstatus/{id}")
	public List<payment> getPayment(@PathVariable String id) {
		service.getpaymentdetails(id);
		
		return service.getpaymentdetailreturn(id);
		
	}
	
	
	
	//payment
	
	  @GetMapping("/price/{bookingid}/{total}") 
	  public String getPayment1(@PathVariable String bookingid,@PathVariable int total) {
	  
	  return service.payment(bookingid, total);
	  
	  }
	  
	  //check-in
	  @GetMapping("/checkin/{paymentid}") 
	  public String checkin(@PathVariable String paymentid) {
		  
		return service.checkIn(paymentid);
		  
	  }
	 
}
