package model;

import java.util.Objects;

public class CustomerModel {
	private int ID;
	private String FullName,Address,PhoneNumber,Email;
	
	public CustomerModel() {
		
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}


	public String getFullName() {
		return FullName;
	}

	public void setFullName(String FullName) {
		this.FullName = FullName;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public CustomerModel(int ID, String ho, String FullName, String address, String phoneNumber, String email) {
		this.ID = ID;
		this.FullName = FullName;
		Address = address;
		PhoneNumber = phoneNumber;
		Email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [ID=" + ID+ ",FullNameCustomer=" + FullName +
				",Diachi="+ Address +",SoDT="+ PhoneNumber +",Email"+ Email +"]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ID,FullName,Address,PhoneNumber,Email);
	}

//		private ArrayList<Customer> list;
//		private String Choice;
//
//		public void insert(Customer Customer) {
//			this.list.add(Customer);
//		}
//		
//		public void delete(Customer Customer) {
//			this.list.remove(Customer);
//		}
//		
//		public void modify(Customer Customer) {
//			this.list.remove(Customer);
//			this.list.add(Customer);
//		}
//
//		public CustomerModel(ArrayList<Customer> list, String choice) {
//			this.list = list;
//			Choice = choice;
//		}
//
//		public String getChoice() {
//			return Choice;
//		}
//
//		public void setChoice(String Choice) {
//			this.Choice = Choice;
//		}
//		public ArrayList getlist() {
//			return list;
//		}
//		public CustomerModel(ArrayList list) {
//			this.list=list;
//		}

//		public boolean kiemTraTonTai(Customer kh) {
//			for(Customer Customer: list) {
//				if(Customer.getMaKH() == kh.getMaKH())
//					return true;
//			}
//			return false;
//		}
		
	}
		

	
