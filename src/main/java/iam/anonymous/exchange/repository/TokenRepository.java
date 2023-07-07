package iam.anonymous.exchange.repository;

import iam.anonymous.exchange.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token getById(Long id);
}
