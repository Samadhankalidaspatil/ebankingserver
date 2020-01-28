package com.app.dao;

import java.util.List;

import com.app.pojos.Account;
import com.app.pojos.Address;
import com.app.pojos.Details;
import com.app.pojos.Loan;

public interface IAdminDao 
{
	Details admin(String email,String password);
	List<Details> getAllCustomers();
	List<Details> getAllStaff();
	void addNewStaff(Details det);
	Loan loanApproving(int lid);
	Account findAccount(int acc_id);
	void changeStatusByAdmin(Loan ln);
	void setChangesAmount(Account acc);
	List<Loan> getLoanList();
	void deleteStaff(int id);
	void newCustomers(Details detail, Address add);
	Details updateDetailOfCustomer(int id);
	Details saveChanges(Details d, Details det);

}