package com.myretail.products;

import java.util.List;

import com.myretail.exception.MyRetailException;

public interface ProductDAO {
	
	public List<ProductDescription> findAll();

	public void create(ProductDescription p) throws MyRetailException;
	
	public ProductDescription readById(long productID);
	
	public void update(ProductDescription p);
	
	public int deleteById(String id);
}
