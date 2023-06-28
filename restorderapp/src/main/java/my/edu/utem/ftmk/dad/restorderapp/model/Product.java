package my.edu.utem.ftmk.dad.restorderapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="productId")
	private int ProductId;
	
	@Column (name="name")
	private String name;
	
	@Column (name="price")
	private float price;
	
	@Column (name="productType")
	private int ProductType;
	
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		this.ProductId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getProductType() {
		return ProductType;
	}
	public void setProductType(int productType) {
		this.ProductType = productType;
	}
	
	
}
