package com.nab.restapi.groceryHelper.model;

import javax.persistence.*;

@Entity(name = "GROCERY_ITEM")
public class GroceryItem {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private int quantity;
	private String category;

	

	public GroceryItem(String name, int quantity,String category) {
		this.name = name;
		this.quantity = quantity;
	this.category=category;
	}

	public GroceryItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	

}
