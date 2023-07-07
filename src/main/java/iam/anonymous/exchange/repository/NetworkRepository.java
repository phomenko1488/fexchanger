package iam.anonymous.exchange.repository;

import iam.anonymous.exchange.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Long> {
    Network getById(Long id);
}
