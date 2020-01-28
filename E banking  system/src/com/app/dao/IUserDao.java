package com.app.dao;

import java.util.List;

import com.app.pojos.Details;

public interface IUserDao
{

	List<Details> getAllUser();

	Details validateUser(String email, String password);

	Details setPasswordNew(String email);

	

	void addPassword(Details d);

	Details getUserWithAddress(int id);
	
}
