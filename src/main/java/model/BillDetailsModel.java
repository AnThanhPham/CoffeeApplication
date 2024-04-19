package model;

public class BillDetailsModel {
	private int ID;
	private int QuantityProduct;
	private BillModel Bill;
	private ProductModel Product;
	private Integer BillID;
	private Integer ProductID;
	
	
	public BillDetailsModel(int iD, int quantityProduct, BillModel bill, ProductModel product) {
		ID = iD;
		QuantityProduct = quantityProduct;
		Bill = bill;
		Product = product;
	}

	public BillDetailsModel() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getQuantityProduct() {
		return QuantityProduct;
	}

	public void setQuantityProduct(int quantityProduct) {
		QuantityProduct = quantityProduct;
	}

	public BillModel getBill() {
		return Bill;
	}

	public void setBill(BillModel bill) {
		Bill = bill;
	}

	public ProductModel getProduct() {
		return Product;
	}

	public void setProduct(ProductModel product) {
		Product = product;
	}

	public Integer getBillID() {
		return BillID;
	}

	public void setBillID(Integer billID) {
		BillID = billID;
	}

	public Integer getProductID() {
		return ProductID;
	}

	public void setProductID(Integer productID) {
		ProductID = productID;
	}
	
	
}
