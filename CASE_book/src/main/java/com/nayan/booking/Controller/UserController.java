package com.nayan.booking.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nayan.booking.Security.JwtUtils;
import com.nayan.booking.Service.UserServices;
import com.nayan.booking.Service.jwtUserDetailService;
import com.nayan.booking.model.AuthenticationRequest;
import com.nayan.booking.model.AuthenticationResponse;
import com.nayan.booking.model.UserDetail;
import com.nayan.booking.repo.UserDetailsRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserServices userServices;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	UserDetailsRepository userRepo;
	@Autowired
	private jwtUserDetailService jwtUserDetailService;
	
	//Add user details
	@PostMapping("/adduser")
	public String saveUserDetails(@RequestBody UserDetail u ) {
		return userServices.save(u);
	}

	
	  //get flightid,noof passengers for payment microservice
	  
	  @GetMapping("/usergetdata/{id}")
	  public UserDetail getUserDetailpayment(@PathVariable String id)
	  {
			return userServices.getUserDetailsByName(id);

	  }
	 
	//user data 
	@GetMapping("/userdetails/{id}")
	private UserDetail getUserDetails(@PathVariable String id) {
		return userServices.getUserDetailsByName(id);
	}
	
	@GetMapping("/ad")
	public String admin() {
		return "admin";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authRequest) throws Exception{
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username and password", e);
		}
		
		final UserDetails userDetail= jwtUserDetailService.loadUserByUsername(authRequest.getUsername());
		 
		final String jwt= jwtUtil.generateToken(userDetail);
		System.out.println(jwt);

		UserDetail user= userRepo.findByUsername(userDetail.getUsername());
		return 	ResponseEntity.ok(new AuthenticationResponse(jwt, user.getBookingid(), user.getName()));
	}
	
	
	
}
