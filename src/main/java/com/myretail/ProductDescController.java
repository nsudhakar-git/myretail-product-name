package com.myretail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.exception.MyRetailException;
import com.myretail.products.ProductDAO;
import com.myretail.products.ProductDescription;

@RestController
public class ProductDescController {
	static Logger logger = LoggerFactory.getLogger(ProductDescController.class);

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ApplicationContext appContext;

	public ApplicationContext getAppContext() {
		return appContext;
	}

	public void setAppContext(ApplicationContext appContext) {
		this.appContext = appContext;
	}

	@RequestMapping("/")
	public String index() {
		return "My Retail Application to manage Product and prices";
	}

	@RequestMapping(value="/create",produces = "application/text")
	public String addProduct(ProductDescription product) {
		System.out.println("Create product...");
		product = new ProductDescription();
		product.setProductID(1241);
		product.setProductName("TestCreateName");
		try {
			productDAO.create(product);
		} catch ( MyRetailException e) {
			logger.error("Error creating the product",e);
			return "{Error in creating - Duplicate key}";
		}
		return "{Created: " + product.toString()+"}";
	}
	
	@RequestMapping(value = "/product/{productID}", produces = "application/JSON",method=RequestMethod.GET)
	public ProductDescription getProductPrice(@PathVariable(value="productID") long productID) {
		logger.info("Fetch product Name..."+productID);
		ProductDescription prod = productDAO.readById(productID);
		if(prod==null){
			/*prod = new ProductDescription();
			prod.setProductID(productID);
			prod.setProductName("");*/
			throw new ResourceNotFoundException(); 
		}
		return prod;
	}

	@RequestMapping(value = "/product", produces = "application/JSON")
	public List<ProductDescription> listProducts() {
		logger.info("Fetch product Name...");

		return productDAO.findAll();
	}

	@RequestMapping("/update")
	public String updateProduct() {
		logger.info("Update product");

		return "Greetings from Spring Boot!!!!! " + productDAO.findAll();
	}
}
