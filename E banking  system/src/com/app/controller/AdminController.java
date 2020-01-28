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

import com.app.dao.IAdminDao;
import com.app.pojos.Account;
import com.app.pojos.Address;
import com.app.pojos.CommonDetail;
import com.app.pojos.Details;
import com.app.pojos.Loan;
import com.app.pojos.LoanStatus;
import com.app.pojos.Role;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IAdminDao admindao;

	@PostMapping("/adinfo")
	public ResponseEntity<?> adminDetail(@RequestBody Details detail) {
		Details dt = admindao.admin(detail.getEmail(), detail.getPassword());
		if (dt == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Details>(dt, HttpStatus.OK);
	}

	@GetMapping("/custlist")
	public ResponseEntity<?> listCustomers() {
		System.out.println("in list customers");
		List<Details> allCust = admindao.getAllCustomers();
		if (allCust == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Details>>(allCust, HttpStatus.OK);
	}

	@GetMapping("/adminlist")
	public ResponseEntity<?> listStaff() {
		System.out.println("in list customers");
		List<Details> allStaff = admindao.getAllStaff();
		if (allStaff == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Details>>(allStaff, HttpStatus.OK);

	}

	@PostMapping("/addstaff")
	public ResponseEntity<?> addCustomers(@RequestBody CommonDetail common)
	{
		System.out.println("inside the add customer method of the staff");
		
         Details detail = new Details(common.getFirstName(),common.getLastName(),common.getMobileNo(),
        		 common.getEmail(),common.getGender(),common.getDob(),common.getAadharNo(),common.getPassword());
         detail.setRoletype(Role.STAFF);
        
         Address add  = new Address(common.getHouseNo(),common.getCity(),common.getState(),common.getPincode());
         
         admindao.newCustomers(detail,add);
		
       return new ResponseEntity<Void>(HttpStatus.OK);	
	}
	
	@GetMapping("/loanlist")
	public ResponseEntity<?> loanList()
	{
		System.out.println("List of loan for staff");
		List<Loan> ln = admindao.getLoanList();
		if(ln == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Loan>>(ln,HttpStatus.OK);	
	}

	@GetMapping("/verifyAfterStaff/{lid}")
	public ResponseEntity<?> verifyLoan(@PathVariable int lid) {
		Loan ln = admindao.loanApproving(lid);

		System.out.println("detail of loan " + ln);
		LoanStatus status = ln.getStatus();

		//if (status == LoanStatus.VERIFIED) {
			ln.setStatus(LoanStatus.APPROVED);
			// admindao.changeStatusByAdmin(ln);
			/*
			 * if(ln.getStatus()==LoanStatus.APPROVED) {
			 */
			int acc_id = ln.getAccId();
			System.out.println("id " + acc_id);
			double amount = ln.getAmount();
			Account acc = admindao.findAccount(acc_id);
			System.out.println("account " + acc);
			double acc_bal = acc.getAccount_bal();
			// acc.setAcctype(AccountType.LOAN);
			double total = amount + acc_bal;
			acc.setAccount_bal(total);
			admindao.changeStatusByAdmin(ln);
			admindao.setChangesAmount(acc);

			// }
		//} 
		System.out.println("in the verify loan");
		return new ResponseEntity<Loan>(ln, HttpStatus.OK);

	}
	
	@GetMapping("/rejectAfterStaff/{lid}")
	public ResponseEntity<?> rejectLoan(@PathVariable int lid) {
		Loan ln = admindao.loanApproving(lid);

		System.out.println("detail of loan " + ln);
		LoanStatus status = ln.getStatus();

		if (status == LoanStatus.VERIFIED || status  != LoanStatus.REJECTED || status  != LoanStatus.NOT_APPROVED) 
		{
			ln.setStatus(LoanStatus.REJECTED);
		} 
		System.out.println("in the verify loan");
		return new ResponseEntity<Loan>(ln, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable int id)
	{
		admindao.deleteStaff(id);
		return null;
	}
	
	@PostMapping("/updatedetailstaff/{id}")
	public ResponseEntity<?> updateDetail(@PathVariable int id, @RequestBody Details d)
	{
		System.out.println("Inside update");
		System.out.println("alela id "+ id + "alela object cha id "+ d.getId());
		System.out.println(d.toString());
		Details det = admindao.updateDetailOfCustomer(id);
		//det.setLastName(d.getLastName());
		Details ud = admindao.saveChanges(d,det);
		System.out.println("bhavaaaaa "+ ud.toString());
		System.out.println("updated member is "+det.toString());
		if(ud == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Details>(ud,HttpStatus.OK);
	}
	
	@GetMapping("/getdetailstaff/{id}")
	public ResponseEntity<?> getById(@PathVariable int id)
	{
		Details d =  admindao.updateDetailOfCustomer(id);
		if(d == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Details>(d,HttpStatus.OK);
	}
	
}