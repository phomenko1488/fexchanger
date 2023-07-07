package iam.anonymous.exchange.service;

import iam.anonymous.exchange.domain.Address;
import iam.anonymous.exchange.domain.Network;

public interface AddressService {
    Address getAddressByNetwork(Network network);
}
