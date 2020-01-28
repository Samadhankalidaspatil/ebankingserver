
package com.app.pojos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="detail")
public class Details 
{
	private Integer id;
	
	private String firstName;
	
	private String lastName;
	private long mobileNo;
	@NotEmpty(message="Email must be supplied")
	@Email(message="Invalid email format")
	private String email;
	
	private String gender;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")//SDF sdf=new SDF("yyyy-MM-dd")
	private Date dob;
	
	private long aadharNo;
	private Address address;
	
	@Pattern(regexp="((?=.*\\d)(?=.*[a-z])(?=.*[#@$*]).{5,20})",message="Invalid Password format")
	private String password;
	
	@NotNull(message="Role must be chosen")
	private Role roletype;
	
	
	
	private List<Account> list = new ArrayList<>();
	private List<Loan> loan = new ArrayList<>();
	
	
	public Details() 
	{
		System.out.println("inside the constructoe of the details");
	}


	

    public Details(String firstName, String lastName, long mobileNo, String email, String gender, Date dob,
			long aadharNo, String password) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.gender = gender;
		this.dob = dob;
		this.aadharNo = aadharNo;
		this.password = password;
		//this.roletype = roletype;
		//this.image = image;
	}




	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

    @Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	 @Column(name="last_name")
	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    @Column(name="mobile_no" ,length=10)
	public long getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(length = 30, unique = true)
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="genger")
	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}

    @Column(length=12)
	public long getAadharNo() {
		return aadharNo;
	}


	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	@JsonIgnore
	@OneToOne(mappedBy = "detail",cascade = CascadeType.ALL, orphanRemoval = true )
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	@Column(length = 20)
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20)
	public Role getRoletype() {
		return roletype;
	}


	public void setRoletype(Role roletype) {
		this.roletype = roletype;
	}

	
	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Account> getList() {
		return list;
	}


	public void setList(List<Account> list) {
		this.list = list;
	}


	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "detail", cascade = CascadeType.ALL,orphanRemoval = true)
	public List<Loan> getLoan() {
		return loan;
	}


	public void setLoan(List<Loan> loan) {
		this.loan = loan;
	}
	
	public void addAddress(Address a) 
	{
		//Details---> address
		this.address= a;
		a.setDetail(this);// address ---> Details
	}
	
	public void removeAddress(Address a) {
		address = null;
		a.setDetail(null);
	}

	public void addAccount(Account acc) {
		list.add(acc);// course--->student
	    acc.setDetail(this);// student-->course

	}

	public void removeStudent(Account acc) {
		list.remove(acc);
		acc.setDetail(null);
	}

	public void addLoan(Loan ln)
    {
        loan.add(ln);
        ln.setDetail(this);

    }

    public void removeLoan(Loan ln)
    {
        loan.remove(ln);
        ln.setDetail(null);
    }

	@Override
	public String toString() {
		return "Details [firstName=" + firstName + ", lastName=" + lastName + ", mobileNo=" + mobileNo + ", email="
				+ email + ", gender=" + gender + ", dob=" + dob + ", aadharNo=" + aadharNo + ", password=" + password
				+  ", list=" + list + ", loan=" + loan + "]";
	}


	
	
	

}