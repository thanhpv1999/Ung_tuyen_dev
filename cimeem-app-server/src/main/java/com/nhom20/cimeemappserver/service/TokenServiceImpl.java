package com.nhom20.cimeemappserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nhom20.cimeemappserver.dao.TokenDao;
import com.nhom20.cimeemappserver.entity.Token;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenDao tokenRepository;

    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }

    @Override
    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}