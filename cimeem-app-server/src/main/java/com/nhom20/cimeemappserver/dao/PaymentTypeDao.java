package com.nhom20.cimeemappserver.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.nhom20.cimeemappserver.entity.Payment;
import com.nhom20.cimeemappserver.entity.PaymentType;

import javax.transaction.Transactional;

public interface PaymentTypeDao extends JpaRepositoryImplementation<PaymentType, String>{
	
	
}
