
package com.app.dao;

import java.util.List;

import com.app.pojos.Account;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.Transfer;


public interface ICustomerDao 
{
	Details customer(String email,String password);
	void transfer(int send_acc,int rec_acc,double amount,Transfer trans);
	Details account(int id);
	Details cusAcc(int id);
	Details setPasswordNew(String email);
	void addLoanDetailHome(Loan ln, int id);
	void addLoanDetailPersonal(Loan ln, int id);
	void addPassword(Details d);
	void addLoanDetailEdu(Loan ln, int id);
	List<Account> getAccountList(int id);
	List<Loan> getLoanList(int id);
	
	

}
