package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IStaffDao;
import com.app.pojos.Account;
import com.app.pojos.AccountType;
import com.app.pojos.Address;
import com.app.pojos.CommonDetail;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.LoanStatus;
import com.app.pojos.Role;

@RestController
@CrossOrigin
@RequestMapping("/staff")
public class StaffController
{
	@Autowired
	private IStaffDao staffdao;
	
    @PostMapping("/sinfo")
	public ResponseEntity<?> customerDetail(@RequestBody Details detail)
	{
    	System.out.println("ssssssssssssssssssss"+detail);
	  Details dt =  staffdao.staffDetail(detail.getEmail(),detail.getPassword());
	  System.out.println("dsaaaaaaaaa" +dt);
	  if (dt == null)
	  
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	  return new ResponseEntity<Details>(dt,HttpStatus.OK);
	}
	
	@GetMapping("/custinfo")
	public ResponseEntity<?> listCustomers() {
		System.out.println("in list customers");
		List<Details> allCust = staffdao.getAllCustomers();
		return new ResponseEntity<List<Details>>(allCust, HttpStatus.OK);
	}
	
	@PostMapping("/addcust")
	public ResponseEntity<?> addCustomers(@RequestBody CommonDetail common)
	{
		System.out.println("inside the add customer method of the staff");
		
         Details detail = new Details(common.getFirstName(),common.getLastName(),common.getMobileNo(),
        		 common.getEmail(),common.getGender(),common.getDob(),common.getAadharNo(),common.getPassword());
         detail.setRoletype(Role.CUSTOMER);
        
         Address add  = new Address(common.getHouseNo(),common.getCity(),common.getState(),common.getPincode());
         
         staffdao.newCustomers(detail,add);
		
       return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@PostMapping("/newaccount/{id}")
	public void addAccount(@RequestBody Account acc,@PathVariable int id)
	{
	  
	  staffdao.startAccount(id,acc);
	}
	
	

	@GetMapping("/loanlist")
	public ResponseEntity<?> loanList()
	{
		System.out.println("List of loan for staff");
		List<Loan> ln = staffdao.getLoanList();
		if(ln == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Loan>>(ln,HttpStatus.OK);
		
	}
	


	@GetMapping("/verify/{id}")
	public ResponseEntity<?> verifyLoan(@PathVariable int id)
	{
		 Loan ln = staffdao.loanProcess(id);
		 
	    double income = ln.getIncome();
	    System.out.println("Inside verify method");
	    if(income > 30000 && LoanStatus.NOT_APPROVED == ln.getStatus() )
	    {
	    	if( LoanStatus.APPROVED != ln.getStatus() || LoanStatus.REJECTED != ln.getStatus())
	    	{
	    	ln.setStatus(LoanStatus.VERIFIED);
	    	ln.getAmount();
	    	staffdao.changeStatus(ln);
	    	}
	    }
		System.out.println("in the verify loan");
		return new ResponseEntity<Loan>(ln,HttpStatus.OK);
		
	}
	
	@GetMapping("/reject/{id}")
	public ResponseEntity<?> rejectLoan(@PathVariable int id)
	{
		 Loan ln = staffdao.loanProcess(id);
		 
	    double income = ln.getIncome();
	    System.out.println("Inside verify method");
	    if(LoanStatus.APPROVED != ln.getStatus() || LoanStatus.NOT_APPROVED == ln.getStatus() ||  LoanStatus.VERIFIED == ln.getStatus())
	    {
	    	ln.setStatus(LoanStatus.REJECTED);
	    	ln.getAmount();
	    	staffdao.changeStatus(ln);
	    }
	   	System.out.println("in the verify loan reject");
		return new ResponseEntity<Loan>(ln,HttpStatus.OK);
		
	}
	
	@PostMapping("/addbalance")
    public ResponseEntity<?> addBalance(@RequestBody Account account)
    {
		System.out.println("inside the staff controller");
		double newbalance = account.getAccount_bal();
		int account_no = account.getAccount_no();
	Account acc = staffdao.addAccountBalance(account_no);
	double amount = acc.getAccount_bal();
	double bal = newbalance + amount;
	acc.setAccount_bal(bal);
    staffdao.balanceOfAccount(acc);
    return new ResponseEntity<Void>(HttpStatus.OK);
	}
    
	@PostMapping("/deductbalance")
	public ResponseEntity<?> reduceBalance(@RequestBody Account account)
	{
		System.out.println("inside the staff controller reduce balance");
		double newbalance = account.getAccount_bal();
		int account_no = account.getAccount_no();
		Account acc = staffdao.reduceAccountBalance(account_no);
		double amount = acc.getAccount_bal();
		double bal = amount - newbalance;
		acc.setAccount_bal(bal);
		staffdao.balanceOfAccount(acc);
	    return new ResponseEntity<Void>(HttpStatus.OK);
		
	}

	@GetMapping("/accountlist")
	public ResponseEntity<?> getAccountList()
	{
		System.out.println("inside the account list method of staff");
		List<Account> account = staffdao.allAccountList();
		if(account == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Account>>(account,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id)
	{
		staffdao.deleteCustomer(id);
		return null;
	}
	
	@PostMapping("/updatedetail/{id}")
	public ResponseEntity<?> updateDetail(@PathVariable int id, @RequestBody Details d)
	{
		System.out.println("Inside update");
		System.out.println("alela id "+ id + "alela object cha id "+ d.getId());
		System.out.println(d.toString());
		Details det = staffdao.updateDetailOfCustomer(id);
		//det.setLastName(d.getLastName());
		Details ud = staffdao.saveChanges(d,det);
		System.out.println("bhavaaaaa "+ ud.toString());
		System.out.println("updated member is "+det.toString());
		if(ud == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Details>(ud,HttpStatus.OK);
	}
	
	@GetMapping("/getdetail/{id}")
	public ResponseEntity<?> getById(@PathVariable int id)
	{
		Details d =  staffdao.updateDetailOfCustomer(id);
		if(d == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Details>(d,HttpStatus.OK);
	}
	
	
	@PostMapping("/updatecustomer")
	public void updateCustomerDetail(@RequestBody Details detail)
	{
		String email = detail.getEmail();
		 Details dt=staffdao.updateCustomer(email);
		 dt.setFirstName(detail.getFirstName());
	        dt.setLastName(detail.getLastName());
	        dt.setMobileNo(detail.getMobileNo());
	        
	        staffdao.setChangesOfUpdate(dt);
	       
	}
	
}
