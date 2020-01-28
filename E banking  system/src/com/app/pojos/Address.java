package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address
{
	private Integer addId;
	private String houseNo;
	private String city;
	private String state;
	private long pincode;
	private Details detail;
	
	
	public Address()
	{
		System.out.println("inside the construtor of address");
	}


	public Address(String houseNo, String city, String state, long pincode) {
		super();
		this.houseNo = houseNo;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}
	
	
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getAddId() {
		return addId;
	}


	public void setAddId(Integer addId) {
		this.addId = addId;
	}


	@Column(name="house_no")
	public String getHouseNo() {
		return houseNo;
	}


	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

    @Column(name="city")
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

    @Column(name="state")
	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

    @Column(name="pin_code",length=6)
	public long getPincode() {
		return pincode;
	}


	public void setPincode(long pincode) {
		this.pincode = pincode;
	}

	
	
	@OneToOne
	@JoinColumn(name="id")
	public Details getDetail() {
		return detail;
	}


	public void setDetail(Details detail) {
		this.detail = detail;
	}


	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", city=" + city + ", state=" + state + ", pincode=" + pincode + "]";
	}
	
	
	
	

}