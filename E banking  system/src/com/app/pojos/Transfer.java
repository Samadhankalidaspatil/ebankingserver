
package com.app.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="transfer")
public class Transfer 
{
	private Integer id;
	private int sender_acc_no;
	private int rec_acc_no;
	private String acc_type;
	private double amount;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date tx_date;

	private String remark;
	
	public Transfer()
	{
		System.out.println("in the constructor of the transfer");
		
	}

	public Transfer(int sender_acc_no, int rec_acc_no, String acc_type, double amount, Date tx_date,
			String remark) {
		super();
		this.sender_acc_no = sender_acc_no;
		this.rec_acc_no = rec_acc_no;
		this.acc_type = acc_type;
		this.amount = amount;
		this.tx_date = tx_date;
		
		this.remark = remark;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="s_acc")
	@Transactional
	public int getSender_acc_no() {
		return sender_acc_no;
	}

	public void setSender_acc_no(int sender_acc_no) {
		this.sender_acc_no = sender_acc_no;
	}

	@Column(name="r_acc")
	public int getRec_acc_no() {
		return rec_acc_no;
	}

	public void setRec_acc_no(int rec_acc_no) {
		this.rec_acc_no = rec_acc_no;
	}

	@Column(name="acc_type")
	public String getAcc_type() {
		return acc_type;
	}

	public void setAcc_type(String acc_type) {
		this.acc_type = acc_type;
	}

	@Column(name="amount")
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tranx_date")
	public Date getTx_date() {
		return tx_date;
	}

	public void setTx_date(Date tx_date) {
		this.tx_date = tx_date;
	}


	@Column(name="remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Transfer [sender_acc_no=" + sender_acc_no + ", rec_acc_no=" + rec_acc_no + ", acc_type=" + acc_type
				+ ", amount=" + amount + ", tx_date=" + tx_date + ", remark=" + remark + "]";
	}
	
	
	

}
