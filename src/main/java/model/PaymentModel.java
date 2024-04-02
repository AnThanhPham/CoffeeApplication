package model;

public class PaymentModel {
	private int ID;
	private String PaymentName;

	public PaymentModel(int iD, String paymentName) {
		ID = iD;
		PaymentName = paymentName;
	}
	
	public PaymentModel() {}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getPaymentName() {
		return PaymentName;
	}

	public void setPaymentName(String paymentName) {
		PaymentName = paymentName;
	}
	
	
}
