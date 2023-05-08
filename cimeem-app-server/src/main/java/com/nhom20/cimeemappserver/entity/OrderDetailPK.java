package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailPK implements Serializable{
	private String product;
	private String orders;
	@Override
	public int hashCode() {
		return Objects.hash(product, orders);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailPK other = (OrderDetailPK) obj;
		return Objects.equals(product, other.product) && Objects.equals(orders, other.orders);
	}
	
}
