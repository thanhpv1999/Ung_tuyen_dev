package com.nhom20.cimeemappserver.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TransactionPK implements Serializable{
	private String product;
	private String payments;
	@Override
	public int hashCode() {
		return Objects.hash(product, payments);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionPK other = (TransactionPK) obj;
		return Objects.equals(product, other.product) && Objects.equals(payments, other.payments);
	}
	
	
}
