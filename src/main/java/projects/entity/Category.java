package projects.entity;

public class Category {
	private Integer categoryId;
	private String catergoryname;
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCatergoryname() {
		return catergoryname;
	}
	public void setCatergoryname(String catergoryname) {
		this.catergoryname = catergoryname;
	}
	@Override
	public String toString() {
		return "ID=" + categoryId + ", catergoryname=" + catergoryname;
	}
}

	
