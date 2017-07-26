package com.zcc.model.dao;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by NCP-620 on 2017/7/10.
 */
@Entity
public class Payment {
	@Id
	@GeneratedValue
	private Long id;
	private String date;
	private String price;
	private String detail;
	private String category;
	private String username;

	public Payment() {
	}

	public Payment(String date, String price, String detail, String category) {
		this.date = date;
		this.price = price;
		this.detail = detail;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
