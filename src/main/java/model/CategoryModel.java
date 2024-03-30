package model;

public class CategoryModel {
	private int ID;
	private String CategoryName;
	private String Description;
	
	public CategoryModel(int iD, String categoryName, String description) {
		ID = iD;
		CategoryName = categoryName;
		Description = description;
	}

	public CategoryModel() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}
	
	
}
