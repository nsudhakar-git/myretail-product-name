package com.myretail.products;
/**
 * 
 * @author Sudhakar
 *
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ProductDesc")
public class ProductDescription {

	@Id long productID ;
	String productName;
	
	
	
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	

	
	@Override
	public String toString(){
		return productID+":"+productName;
	}
}
