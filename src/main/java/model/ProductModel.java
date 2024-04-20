package model;

public class ProductModel {
	private int ID;
	private int Price;
	private String Name;
	private String Description;
	private String Image;
	private CategoryModel Category;
	
	
	public ProductModel(int iD, int price, String name, String description, String image, CategoryModel category) {
		ID = iD;
		Price = price;
		Name = name;
		Description = description;
		Image = image;
		Category = category;
	}

	public ProductModel() {
		ID= 0;
		Price= 0;
		Name = "";
		Description = "";
		Image = "";
		Category = new CategoryModel(0,"","");
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public CategoryModel getCategory() {
		return Category;
	}

	public void setCategory(CategoryModel category) {
		Category = category;
	}
	
	
}
