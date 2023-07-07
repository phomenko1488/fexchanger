package iam.anonymous.exchange.service.impl;

import iam.anonymous.exchange.domain.Network;
import iam.anonymous.exchange.repository.NetworkRepository;
import iam.anonymous.exchange.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NetworkServiceImpl implements NetworkService {

    private final NetworkRepository repository;

    @Autowired
    public NetworkServiceImpl(NetworkRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Network> getAll() {
        return repository.findAll();
    }
}
