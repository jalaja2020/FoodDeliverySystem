package com.example.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="account_id")
	private Integer accountId;
	
	@Column(name = "acc_number")
	private String accNumber;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "balance")
	private float amount;
	
	@Column(name="phone_no")
	private String phoneNo;

	@Column(name="trans_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date transDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private Users users;

}
