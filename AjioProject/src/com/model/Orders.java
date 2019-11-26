package com.model;
import java.sql.Timestamp;

public class Orders {
	private int id;
	@Override
	public String toString() {
		return "Orders [id=" + id + ", user_id=" + user_id + ", total_amount=" + total_amount + ", order_date="
				+ order_date + "]";
	}
	private Users user_id;
	private int total_amount;
	private Timestamp order_date;
	private String sessionId;

	public Orders() {
		super();		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getUser_id() {
		return user_id;
	}
	public void setUser_id(Users user_id) {
		this.user_id = user_id;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public Timestamp getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}
	public Orders(Users user_id) {
		super();
		this.user_id = user_id;
	}
	public Orders(Users user_id, String sessionId) {
		super();
		this.user_id = user_id;
		this.sessionId = sessionId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public Orders(int id) {
		super();
		this.id = id;
	}
	public Orders(int id,String sessionId) {
		super();
		this.id = id;
		this.sessionId = sessionId;
	}
	public Orders(String sessionId) {
		super();
		this.sessionId = sessionId;
	}
	public Orders(Users user_id, int total_amount, Timestamp order_date, String sessionId) {
		super();
		this.user_id = user_id;
		this.total_amount = total_amount;
		this.order_date = order_date;
		this.sessionId = sessionId;
	}
	public Orders(int id, Users user_id, int total_amount, Timestamp order_date) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.total_amount = total_amount;
		this.order_date = order_date;
	}
	
}
