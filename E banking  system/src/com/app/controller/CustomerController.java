package com.app.controller;

import java.util.List;

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

import com.app.dao.ICustomerDao;
import com.app.pojos.Account;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.LoanStatus;
import com.app.pojos.LoanType;
import com.app.pojos.Transfer;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController
{
	@Autowired
	private ICustomerDao cusdao;
	
	@PostMapping("/info")
	public ResponseEntity<?> customerDetail(@RequestBody Details detail)
	{
	  Details dt =  cusdao.customer(detail.getEmail(),detail.getPassword());
	  
	  return new ResponseEntity<Details>(dt,HttpStatus.OK);
	}
	
	
	@PostMapping("/transfer")
	public ResponseEntity<?> transferDetail(@RequestBody Transfer trans)
	{
		System.out.println("sdad  "+trans);
		int sAccNo=trans.getSender_acc_no();
		int rAccNo=trans.getRec_acc_no();
		double amount = trans.getAmount();
		cusdao.transfer(sAccNo, rAccNo, amount,trans);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	
	@GetMapping("/acc/{id}")//Account detail from customer
	public ResponseEntity<?> accountDetail(@PathVariable int id)
	{
	  Details detail =   cusdao.account(id);
	  return new ResponseEntity<Details>(detail,HttpStatus.OK);
	}
	
	
	@GetMapping("/loancust/{id}")
    public ResponseEntity<?> customerAccount(@PathVariable int id)
    {
		Details det = cusdao.cusAcc(id);
		return new ResponseEntity<Details>(det,HttpStatus.OK);
		
    }
	
	
	@PostMapping("/loan/education/{id}")
	public ResponseEntity<?> applyLoanEdu(@RequestBody Loan ln,@PathVariable int id)
	{
		ln.setLoanType(LoanType.EDUCATION);
		ln.setStatus(LoanStatus.NOT_APPROVED);
		cusdao.addLoanDetailEdu(ln,id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	
	@PostMapping("/loan/home/{id}")
	public ResponseEntity<?> applyLoanHome(@RequestBody Loan ln,@PathVariable int id)
	{
		ln.setLoanType(LoanType.HOME);
		ln.setStatus(LoanStatus.NOT_APPROVED);
		cusdao.addLoanDetailHome(ln,id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	
	@PostMapping("/loan/personal/{id}")
	public ResponseEntity<?> applyLoanPersonal(@RequestBody Loan ln,int id)
	{
		//double personal = 12.3; 
		ln.setLoanType(LoanType.PERSONAL);
		ln.setStatus(LoanStatus.NOT_APPROVED);
		//ln.setInterest_rate(personal);
		cusdao.addLoanDetailPersonal(ln,id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	
	}
	
	
	 @PostMapping("/setPass")
	    public ResponseEntity<?> newPassword(@RequestBody Details det)
	    {
			String email = det.getEmail();
			String password = det.getPassword();
			
			Details d = cusdao.setPasswordNew(email);
			if(d == null)
				return new ResponseEntity<Void>(HttpStatus.OK);
			d.setPassword(password);
			cusdao.addPassword(d);
			return new ResponseEntity<Details>(d,HttpStatus.OK);
	    }
	 
	 @GetMapping("/accountdetail/{id}")
		public ResponseEntity<?> accountList(@PathVariable int id)
		{
			List<Account> detail = cusdao.getAccountList(id);
			System.out.println("List of account "+detail);
			if(detail == null)
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Account>> (detail,HttpStatus.OK);
				
		}
	 

    @GetMapping("/loanlist/{id}")
	public ResponseEntity<?> loanList(@PathVariable int id)
	{	
	    List<Loan> loan = cusdao.getLoanList(id);
	    System.out.println("List of account "+loan);
		if(loan == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Loan>> (loan,HttpStatus.OK);
	
	}
	
	
		
	

}
