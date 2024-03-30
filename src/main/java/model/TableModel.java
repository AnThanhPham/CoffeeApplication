package model;

public class TableModel {
	private int ID;
	private String TableNumber;
	private String Status;
	private int QuantityCustomer;
	
	
	
	public TableModel(int iD, String tableNumber, String status, int quantityCustomer) {
		ID = iD;
		TableNumber = tableNumber;
		Status = status;
		QuantityCustomer = quantityCustomer;
	}

	public TableModel() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTableNumber() {
		return TableNumber;
	}

	public void setTableNumber(String tableNumber) {
		TableNumber = tableNumber;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public int getQuantityCustomer() {
		return QuantityCustomer;
	}

	public void setQuantityCustomer(int quantityCustomer) {
		QuantityCustomer = quantityCustomer;
	}
	
	
}
