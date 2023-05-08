package com.nhom20.cimeemappserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.AboutDao;
import com.nhom20.cimeemappserver.dao.MenusDao;
import com.nhom20.cimeemappserver.dao.ProductDao;
import com.nhom20.cimeemappserver.entity.About;
import com.nhom20.cimeemappserver.entity.Menus;
import com.nhom20.cimeemappserver.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	public Page<Product> listProduct(Pageable pageable) {
		// TODO Auto-generated method stub
		return productDao.findAll(pageable);
	}

	@Override
	public List<Product> listProductByHeader(String headerId) {
		// TODO Auto-generated method stub
		return productDao.listProductByHeader(headerId);
	}

	@Override
	public void updateProduct(String gen, String gen2, String gen3, String gen4, String id) {
		// TODO Auto-generated method stub
		productDao.updateProduct(gen, gen2, gen3, gen4, id);
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		productDao.save(product);
	}

	@Override
	public void deleteService(String id) {
		// TODO Auto-generated method stub
		productDao.deleteById(id);
	}

	@Override
	public List<Product> listProduct() {
		// TODO Auto-generated method stub
		return productDao.findAll();
	}

	@Override
	public List<Product> listById(String string) {
		// TODO Auto-generated method stub
		return productDao.listById(string);
	}

}
