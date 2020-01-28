
package com.app.pojos;

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
@Table(name="loan")
public class Loan 
{
	private Integer lid;
	private LoanType loanType;
	private Details detail;
	private double amount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private int loan_period;
	private double interest_rate;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date applied_date;
	private LoanStatus status;
	private String occupation;
	private String organization;
	private double income;
	private int accId;
	
	
	public Loan() 
	{
		System.out.println("inside the constructor of the Loan");
	}


	public Loan(LoanType loanType, double amount, int loan_period, double interest_rate,
			Date applied_date, LoanStatus status, String occupation, String organization, double income,int accId) {
		super();
		this.loanType = loanType;
		this.amount = amount;
		this.loan_period = loan_period;
		this.interest_rate = interest_rate;
		this.applied_date = applied_date;
		this.status = status;
		this.occupation = occupation;
		this.organization = organization;
		this.income = income;
		this.accId = accId;
	}

    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getLid() {
		return lid;
	}


	public void setLid(Integer lid) {
		this.lid = lid;
	}

    
	@Enumerated(EnumType.STRING)
	@Column(name = "loan_type", length = 20)
	public LoanType getLoanType() {
		return loanType;
	}


	public void setLoanType(LoanType loanType) {
		this.loanType = loanType;
	}



    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="detail")
    public Details getDetail() {
		return detail;
	}


	public void setDetail(Details detail) {
		this.detail = detail;
	}


	@Column(name="amount")
	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	@Column(name = "loan_period")
	public int getLoan_period() {
		return loan_period;
	}


	public void setLoan_period(int loan_period) {
		this.loan_period = loan_period;
	}

    @Column(name="interest")
	public double getInterest_rate() {
		return interest_rate;
	}


	public void setInterest_rate(double interest_rate) {
		this.interest_rate = interest_rate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "applied_date")
	public Date getApplied_date() {
		return applied_date;
	}


	public void setApplied_date(Date applied_date) {
		this.applied_date = applied_date;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "loan_status", length = 20)
	public LoanStatus getStatus() {
		return status;
	}


	public void setStatus(LoanStatus status) {
		this.status = status;
	}

    @Column(name="occupation")
	public String getOccupation() {
		return occupation;
	}


	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

 
	@Column(name="organization")
	public String getOrganization() {
		return organization;
	}


	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	@Column(name="income")
    public  double getIncome() {
		return income;
	}


	public void setIncome(double income) {
		this.income = income;
	}

	
	
    @Column(name="acc_id")
	public int getAccId() {
		return accId;
	}


	public void setAccId(int accId) {
		this.accId = accId;
	}


	@Override
	public String toString() {
		return "Loan [lid=" + lid + ", loanType=" + loanType + ", amount=" + amount + ", loan_period=" + loan_period
				+ ", interest_rate=" + interest_rate + ", applied_date=" + applied_date + ", status=" + status
				+ ", occupation=" + occupation + ", organization=" + organization + ", income=" + income + ", accId="
				+ accId + "]";
	}


	

	


	

	
	
	

}
