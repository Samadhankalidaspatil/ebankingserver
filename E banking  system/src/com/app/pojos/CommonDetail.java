package com.app.pojos;

import java.util.Date;

public class CommonDetail 
{
	private String firstName;
	private String lastName;
	private long mobileNo;
    private String email;
	private String gender;
    private Date dob;
	private long aadharNo;
	private String houseNo;
	private String city;
	private String state;
	private long pincode;
	private String acc_holder_name;
	private String bank_add;
	private String ifsc;
	private double account_bal;
	private String accountType;
	private Date opening_date;
	private String password;
	private byte[] image;
	
	

	public CommonDetail() {
		System.out.println("inside the common detail constructor");
	}


	public CommonDetail(String firstName, String lastName, long mobileNo, String email, String gender, Date dob,
			long aadharNo, String houseNo, String city, String state, long pincode, String acc_holder_name,
			String bank_add, String ifsc, double account_bal, String accountType, Date opening_date, String password,
			byte[] image) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.aadharNo = aadharNo;
		this.houseNo = houseNo;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.acc_holder_name = acc_holder_name;
		this.bank_add = bank_add;
		this.ifsc = ifsc;
		this.account_bal = account_bal;
		this.accountType = accountType;
		this.opening_date = opening_date;
		this.password = password;
		this.image = image;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public long getPincode() {
		return pincode;
	}

	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	public String getAcc_holder_name() {
		return acc_holder_name;
	}

	public void setAcc_holder_name(String acc_holder_name) {
		this.acc_holder_name = acc_holder_name;
	}

	public String getBank_add() {
		return bank_add;
	}

	public void setBank_add(String bank_add) {
		this.bank_add = bank_add;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public double getAccount_bal() {
		return account_bal;
	}

	public void setAccount_bal(double account_bal) {
		this.account_bal = account_bal;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getOpening_date() {
		return opening_date;
	}

	public void setOpening_date(Date opening_date) {
		this.opening_date = opening_date;
	}
	
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CommonDetail [firstName=" + firstName + ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", email="
				+ email + ", gender=" + gender + ", dob=" + dob + ", aadharNo=" + aadharNo + ", houseNo=" + houseNo
				+ ", city=" + city + ", state=" + state + ", pincode=" + pincode + ", acc_holder_name="
				+ acc_holder_name + ", bank_add=" + bank_add + ", ifsc=" + ifsc + ", account_bal=" + account_bal
				+ ", accountType=" + accountType + ", opening_date=" + opening_date + ", password=" + password + "]";
	}

	
	
	
	

}