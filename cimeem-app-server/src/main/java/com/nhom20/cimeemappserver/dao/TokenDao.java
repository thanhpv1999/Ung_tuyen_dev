package com.nhom20.cimeemappserver.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nhom20.cimeemappserver.entity.Token;

public interface TokenDao extends JpaRepository<Token, Long> {
	Token findByToken(String token);
}