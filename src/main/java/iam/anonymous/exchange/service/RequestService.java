package iam.anonymous.exchange.service;

import iam.anonymous.exchange.dto.RequestCreateDTO;
import iam.anonymous.exchange.domain.Request;
import iam.anonymous.exchange.dto.GetRequestDTO;
import iam.anonymous.exchange.dto.RequestCancelDTO;

public interface RequestService {
    Request create(RequestCreateDTO dto) throws IllegalArgumentException;
    Request cancel(RequestCancelDTO dto);

    GetRequestDTO getById(String id);
}
