package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class ProductPricePk implements Serializable{

	private String product;
	private String price;
	@Override
	public int hashCode() {
		return Objects.hash(price, product);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductPricePk other = (ProductPricePk) obj;
		return Objects.equals(price, other.price) && Objects.equals(product, other.product);
	}
	
}
