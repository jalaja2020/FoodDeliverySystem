package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name="vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vendors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="vendorId")
	Integer id;

	@Column(name="vendor_name")
	String vendorName;

}
