package com.app.dao;

import java.util.List;

import com.app.pojos.Account;
import com.app.pojos.Address;
import com.app.pojos.Details;
import com.app.pojos.Loan;

public interface IStaffDao 
{
	
	Details staffDetail(String email,String password);
	List<Details> getAllCustomers();
	void newCustomers(Details det, Address ad, Account ac);
	List<Loan> getLoanList();
	Loan loanProcess(int id);
	void changeStatus(Loan ln);
	Account addAccountBalance(int account_no);
	void balanceOfAccount(Account acc);
	Account reduceAccountBalance(int account_no);
	List<Account> allAccountList();
	void deleteCustomer(int id);
	Details updateDetailOfCustomer(int id);
	Details updateCustomer(String email);
	void setChangesOfUpdate(Details dt);
	void newCustomers(Details detail, Address add);
	void startAccount(int id, Account acc);
	Details saveChanges(Details d, Details det);
	
}
