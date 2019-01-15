package com.qetch.springmvc.service;

import com.qetch.springmvc.domain.Product;

public interface ProductService {
	
	Product add(Product product);
	
	Product get(long id);
}
