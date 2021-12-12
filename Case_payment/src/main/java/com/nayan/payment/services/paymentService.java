package com.nayan.payment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nayan.payment.model.UserDetail;
import com.nayan.payment.model.flightDetails;
import com.nayan.payment.model.payment;
import com.nayan.payment.repo.Paymentrepository;
@Service
public class paymentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private  Paymentrepository paymentrepository;
	
	   //fetch data from booking microservice
	
	    public void getpaymentdetails(String id) {
	    	
		UserDetail userdetail = restTemplate.getForObject("http://case-book/user/usergetdata/"+ id, UserDetail.class);
		System.out.println("hello " + userdetail);
		int nooftravellers = userdetail.getNoOfPassengers();
		String flightIdforprice=userdetail.getFlightId();
		int Tprice=(getprice(flightIdforprice));
		System.out.println("travellers " + nooftravellers);
		System.out.println("price " + Tprice);
		int Totalprice=Tprice*nooftravellers;
		int doinsert=1;
		List<payment> eachdetail=new ArrayList<>();
		paymentrepository.findAll().forEach(pay->eachdetail.add(pay));
    	for(payment p1:eachdetail) {
    	if(p1.getBookinId().equals(id)) {
    		doinsert=0;
    		
    	}
    	}
		if(doinsert==1) {
		payment payment1 = new payment(null, id, flightIdforprice, nooftravellers, Tprice, Totalprice, false, false);
		paymentrepository.save(payment1);
		}
		
	}
	    //return payment details
	    public List<payment> getpaymentdetailreturn(String id) {
	    	List<payment> eachdetail=new ArrayList<>();
	    	List<payment> crtdetail=new ArrayList<>();
	    	
	    	paymentrepository.findAll().forEach(pay->eachdetail.add(pay));
	    	for(payment p1:eachdetail) {
	    		if(p1.getBookinId().equals(id)) {
	    			
	    			crtdetail.add(p1);
	    		}
	    			
	    	}
			return crtdetail;
		}
	    
	  
	////fetch data from searching microservice
	public int getprice(String id) {
		flightDetails f = restTemplate.getForObject("http://case-search/flight/"+id,flightDetails.class);
		System.out.println("hello " + f);
		return f.getPrice();
	}
	
	
	
	//paying amount
	public String payment(String bookingid,int total) {
		
		
		payment payment1= paymentrepository.findById(bookingid).get();
		
		int totalpayment= payment1.getTotalPrice();
		if(totalpayment==total && payment1.isPaymentstatus()==(false)) {
			payment1.setPaymentstatus(true);
			paymentrepository.save(payment1);
			return ("Full Amount Paid with Id: "+ payment1.getPaymentId()); 
		}
		else if(total<totalpayment && payment1.isPaymentstatus()==(false)) {
			return ("Pay full amount " + payment1.getTotalPrice() );
		}
		else if(total>totalpayment && payment1.isPaymentstatus()==(false))
		{
			payment1.setPaymentstatus(true);
			paymentrepository.save(payment1);
			int t= total-totalpayment;
			return ("Full Amount Paid with Id:"+ payment1.getPaymentId()+ " Balance amount: "+ t+ " returned");
			
		}
		return null;		
	}
	public String checkIn(String paymentid) {
		payment checkin = paymentrepository.findById(paymentid).get();
		if(checkin.isPaymentstatus()==false) {
			System.out.println("/////");
			return "Payment is not done yet";
	
	}
		else {
			checkin.setCheckinstatus(true);
			paymentrepository.save(checkin);
			return "CheckedIn Successfullly";
		}


	
	}	
}	
