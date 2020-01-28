package com.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IUserDao;
import com.app.pojos.Address;
import com.app.pojos.Details;



@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	private IUserDao dao;
	
	@PostConstruct
	public void init()
	{
		System.out.println("in init" + dao);
	}
	
	@GetMapping
	public ResponseEntity<?> listUsers()
	{
		System.out.println("Inside list books");
		List<Details> allUsers = dao.getAllUser();
		if(allUsers.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Details>>(allUsers,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> processLoginForm(@RequestBody Details u)
	{
		String email = u.getEmail();
		String password = u.getPassword();
		System.out.println("inside login function"+u);
		System.out.println(u.toString());
		
		Details loggedUser = dao.validateUser(email, password);
		System.out.println(loggedUser.toString());
		
		return new ResponseEntity<Details>(loggedUser,HttpStatus.OK);
	}
	
	@PostMapping("/setPass")
    public ResponseEntity<?> newPassword(@RequestBody Details det)
    {
		String email = det.getEmail();
		String password = det.getPassword();
		
		Details d = dao.setPasswordNew(email);
		if(d == null)
			return new ResponseEntity<Void>(HttpStatus.OK);
		d.setPassword(password);
		dao.addPassword(d);
		return new ResponseEntity<Details>(d,HttpStatus.OK);
    }

	@GetMapping("/address/{id}")
	public ResponseEntity<?> getUserAddress(@PathVariable int id )
	{
			System.out.println(" Inside user controller " +id );
			Details u = dao.getUserWithAddress(id);
			System.out.println(u.getAddress());
			if ( u == null )
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<Address>(u.getAddress(), HttpStatus.OK);
	}
}
