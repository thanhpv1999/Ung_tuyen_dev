package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Product;


public interface ProductService {

	public Page<Product> listProduct(Pageable pageable);
	public List<Product> listProductByHeader(String headerId);
	public void updateProduct(String gen,String gen2, String gen3, String gen4,String id);
	public void save(Product product);
	public void deleteService(String id);
	public List<Product> listProduct();
	public List<Product> listById(String string);
}
