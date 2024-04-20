package model;

public class CustomerModel {
	private int ID;
	private String name;
	private String phone;
	private String address;
	private String email;

	
	public CustomerModel(int iD, String name, String phone, String address, String email) {
		ID = iD;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public CustomerModel() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}