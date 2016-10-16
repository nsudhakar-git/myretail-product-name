package com.myretail.products;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.mongodb.WriteResult;
import com.myretail.Application;
import com.myretail.exception.MyRetailException;
@Component
public class ProductDAOImpl implements ProductDAO {
	static Logger logger = LoggerFactory.getLogger(Application.class);
	@Autowired
	private MongoOperations mongoOps;
	
	private static final String PRODUCT_COLLECTION = "ProductDesc";
	
	public ProductDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
	}
	
	
	
	public void create(ProductDescription p) throws MyRetailException {
	try{
		this.mongoOps.insert(p, PRODUCT_COLLECTION);
	}catch(Exception e){
		logger.error("error inserting product",e);
		if(e.getMessage().contains("Duplicate")){
			throw new MyRetailException("Duplicate entry",e);
		}
	}
	}

	
	public ProductDescription readById(long id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoOps.findOne(query, ProductDescription.class, PRODUCT_COLLECTION);
	}

	
	public void update(ProductDescription p) {
		this.mongoOps.save(p, PRODUCT_COLLECTION);
	}

	
	public int deleteById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = this.mongoOps.remove(query, ProductDescription.class, PRODUCT_COLLECTION);
		return result.getN();
	}


	
	public List<ProductDescription> findAll() {
			return mongoOps.findAll(ProductDescription.class,PRODUCT_COLLECTION);
	}



}
