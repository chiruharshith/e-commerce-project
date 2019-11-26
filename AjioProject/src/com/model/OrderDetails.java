package com.model;

public class OrderDetails {
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", order_id=" + order_id + ", product_id=" + product_id + ", quantity="
				+ quantity + "]";
	}
	private int id;
	private Orders order_id;
	private Products product_id;
	private int quantity;
	private Orders sessionId;
	public OrderDetails() {
		super();	
	}
	public Orders getSessionId() {
		return sessionId;
	}
	public void setSessionId(Orders sessionId) {
		this.sessionId = sessionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Orders getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Orders order_id) {
		this.order_id = order_id;
	}
	public Products getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Products product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public OrderDetails(Orders order_id, Products product_id) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
	}
	public OrderDetails(Orders order_id, Orders sessionId) {
		super();
		this.order_id = order_id;
		this.sessionId = sessionId;
	}
	public OrderDetails(Orders order_id, Products product_id, int quantity, Orders sessionId) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.sessionId = sessionId;
	}
	public OrderDetails(int id, Orders order_id, Products product_id, int quantity) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}

}
