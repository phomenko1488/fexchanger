package iam.anonymous.exchange.service.impl;

import iam.anonymous.exchange.domain.Address;
import iam.anonymous.exchange.domain.Network;
import iam.anonymous.exchange.repository.AddressRepository;
import iam.anonymous.exchange.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;

    @Autowired
    public AddressServiceImpl(AddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address getAddressByNetwork(Network network) {
        return repository.getByNetwork(network);
    }
}
