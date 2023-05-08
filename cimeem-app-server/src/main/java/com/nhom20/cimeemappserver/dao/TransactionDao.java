package com.nhom20.cimeemappserver.dao;


import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.nhom20.cimeemappserver.entity.Transaction;


public interface TransactionDao extends JpaRepositoryImplementation<Transaction, String>{
	
	
}
