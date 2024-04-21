package model;

import views.menu.PanelShop;

public class PanelModel {
//	private int productID;
//	private String productName;
//	private float priceProduct;
//	private int quantityProduct;
	private int ID;
	private String Name;
	private float Price;
//	ProductModel productModel = new ProductModel();
//	private BillModel billModel = new BillModel();
//	private BillDetailsModel billDetailsModel = new BillDetailsModel();
//	private PanelShop panelShop = new PanelShop();

	

//	public PanelShop getPanelShop() {
//		return panelShop;
//	}
//
//	public void setPanelShop(PanelShop panelShop) {
//		this.panelShop = panelShop;
//	}
//
//	public PanelModel() {
//		
//	}
//	public PanelModel(int iD, String name, float price) {
//	
//		this.ID = iD;
//		this.Name = name;
//		this.Price = price;
//	}

	public PanelModel(int iD, String name, float price) {
		
		this.ID = iD;
		this.Name = name;
		this.Price = price;
	}
//	public PanelModel(int iD, String name, float price, ProductModel productModel, BillModel billModel,BillDetailsModel billDetailsModel) {
//	super();
//	ID = iD;
//	Name = name;
//	Price = price;
//	this.productModel = productModel;
//	this.billModel = billModel;
//	this.billDetailsModel = billDetailsModel;
//}
	@Override
	public String toString() {
		return "PanelModel [ID=" + ID + ", Name=" + Name + ", Price=" + Price + "]";
	}

//	public int setProductID(int productID) {
//		return this.productID = productID;
//	}
//	public String setProductName(String productName) {
//		return this.productName = productName;
//	}
//	public float setPriceProduct(float priceProduct) {
//		return this.priceProduct = priceProduct;
//	}
//	public int setQuantityProduct(int quantityProduct) {
//		return this.quantityProduct = quantityProduct;
//	}
//	
//	public int getProductId() {
//		return productID;
//	}
//
//	public String getProductName() {
//		return productName;
//	}
//	public float getPriceProduct() {
//		return priceProduct;
//	}
//	public int getQuantityProduct() {
//		return quantityProduct;
//	}
	
	public int getID() {
		return ID;
	}
//	public ProductModel getProductModel() {
//		return productModel;
//	}
//
//	public void setProductModel(ProductModel productModel) {
//		this.productModel = productModel;
//	}
//
//	public BillModel getBillModel() {
//		return billModel;
//	}
//
//	public void setBillModel(BillModel billModel) {
//		this.billModel = billModel;
//	}
//
//	public BillDetailsModel getBillDetailsModel() {
//		return billDetailsModel;
//	}
//
//	public void setBillDetailsModel(BillDetailsModel billDetailsModel) {
//		this.billDetailsModel = billDetailsModel;
//	}

	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}

}
