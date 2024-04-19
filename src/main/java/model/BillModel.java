package model;

import java.sql.Date;

public class BillModel {
	private int ID;
	private Date BillDate;
	private float BillTotal;
	private String status;
	private CustomerModel Customer;
	private UserModel  User;
	private TableModel Table;
	private PaymentModel Payment;
	private Integer CustomerID;
	private Integer UserID;
	private Integer TableID;
	private Integer PaymentID;
	
	public BillModel(int iD, Date billDate, float billTotal, String status, CustomerModel customer, UserModel user,
			TableModel table, PaymentModel payment) {
		ID = iD;
		BillDate = billDate;
		BillTotal = billTotal;
		this.status = status;
		Customer = customer;
		User = user;
		Table = table;
		Payment = payment;
	}

	public BillModel() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Date getBillDate() {
		return BillDate;
	}

	public void setBillDate(Date billDate) {
		BillDate = billDate;
	}

	public float getBillTotal() {
		return BillTotal;
	}

	public void setBillTotal(float billTotal) {
		BillTotal = billTotal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerModel getCustomer() {
		return Customer;
	}

	public void setCustomer(CustomerModel customer) {
		Customer = customer;
	}

	public UserModel getUser() {
		return User;
	}

	public void setUser(UserModel user) {
		User = user;
	}

	public TableModel getTable() {
		return Table;
	}

	public void setTable(TableModel table) {
		Table = table;
	}

	public PaymentModel getPayment() {
		return Payment;
	}

	public void setPayment(PaymentModel payment) {
		Payment = payment;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public int getTableID() {
		return TableID;
	}

	public void setTableID(int tableID) {
		TableID = tableID;
	}

	public int getPaymentID() {
		return PaymentID;
	}

	public void setPaymentID(int paymentID) {
		PaymentID = paymentID;
	}
	
}

