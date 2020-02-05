package com.springproject.eshop.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	private int quantity;
	private BigDecimal price;

	public Item() {
	}

	public Item(String name, int quantity, BigDecimal price) {
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
}
