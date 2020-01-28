
package com.app.pojos;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="account")
public class Account 
{
	private Integer account_no;
	private String acc_holder_name;
	private String bank_add;
	private String ifsc;
	private double account_bal;
	private AccountType acctype;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date opening_date;
	private Details detail;
	
	public Account()
	{
		System.out.println("inside the constructor of account");
	}

	public Account(String acc_holder_name, String bank_add, String ifsc, double account_bal,
			 Date opening_date) {
		super();
		this.acc_holder_name = acc_holder_name;
		this.bank_add = bank_add;
		this.ifsc = ifsc;
		this.account_bal = account_bal;
		
		this.opening_date = opening_date;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAccount_no() {
		return account_no;
	}

	public void setAccount_no(Integer account_no) {
		this.account_no = account_no;
	}

	@Column(name="customer_name")
	public String getAcc_holder_name() {
		return acc_holder_name;
	}

	public void setAcc_holder_name(String acc_holder_name) {
		this.acc_holder_name = acc_holder_name;
	}
   
	@Column(name="bank_add")
	public String getBank_add() {
		return bank_add;
	}

	public void setBank_add(String bank_add) {
		this.bank_add = bank_add;
	}

	
	@Column(name="IFSC",length=11)
	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	
	@Column(name="balance")
	public double getAccount_bal() {
		return account_bal;
	}

	public void setAccount_bal(double account_bal )
	{	
		if( this.account_bal >= 0 )
		{
			this.account_bal = account_bal;
		}
			
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "acc_type", length = 20)
	public AccountType getAcctype() {
		return acctype;
	}

	public void setAcctype(AccountType acctype) {
		this.acctype = acctype;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "opened_on")
	public Date getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(Date d) {
		this.opening_date = d;
	}
	
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	public Details getDetail() {
		return detail;
	}

	public void setDetail(Details detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Account [account_no=" + account_no + ", acc_holder_name=" + acc_holder_name + ", bank_add=" + bank_add
				+ ", ifsc=" + ifsc + ", account_bal=" + account_bal + ", acctype=" + acctype + ",  opening_date=" + opening_date + "]";
	}
	
	
	
	
	

}
