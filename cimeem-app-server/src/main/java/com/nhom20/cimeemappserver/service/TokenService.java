package com.nhom20.cimeemappserver.service;

import com.nhom20.cimeemappserver.entity.Token;

public interface TokenService {
    Token createToken(Token token);

    Token findByToken(String token);
}