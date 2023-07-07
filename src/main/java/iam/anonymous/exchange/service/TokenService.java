package iam.anonymous.exchange.service;

import iam.anonymous.exchange.domain.Token;

import java.util.Collection;

public interface TokenService {
    Collection<Token> getAll();
    Token getById(Long id);
    void updatePrices();
}
