package iam.anonymous.exchange.repository;

import iam.anonymous.exchange.domain.Address;
import iam.anonymous.exchange.domain.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getByNetwork(Network network);
}
