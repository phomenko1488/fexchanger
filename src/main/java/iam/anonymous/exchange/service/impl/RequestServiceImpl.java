package iam.anonymous.exchange.service.impl;

import iam.anonymous.exchange.dto.RequestCreateDTO;
import iam.anonymous.exchange.domain.Request;
import iam.anonymous.exchange.domain.Token;
import iam.anonymous.exchange.dto.GetRequestDTO;
import iam.anonymous.exchange.dto.RequestCancelDTO;
import iam.anonymous.exchange.enums.RequestStatus;
import iam.anonymous.exchange.repository.RequestRepository;
import iam.anonymous.exchange.service.AddressService;
import iam.anonymous.exchange.service.RequestService;
import iam.anonymous.exchange.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static iam.anonymous.exchange.utils.Parser.*;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository repository;
    private final TokenService tokenService;
    private final AddressService addressService;

    @Autowired
    public RequestServiceImpl(RequestRepository repository, TokenService tokenService, AddressService addressService) {
        this.repository = repository;
        this.tokenService = tokenService;
        this.addressService = addressService;
    }

    @Override
    public Request create(RequestCreateDTO dto) throws IllegalArgumentException {
        Long fromId = parseLong(dto.getFromId()), toId = parseLong(dto.getToId());
        Double fromAmount = parseDouble(dto.getTokenFromAmount()), toAmount = parseDouble(dto.getTokenToAmount());
        Token from = tokenService.getById(fromId);
        Token to = tokenService.getById(toId);
        if (fromId == null || toId == null
                || fromAmount == null || toAmount == null
                || from == null || to == null
                || checkInvalidAmount(fromAmount) || checkInvalidAmount(toAmount)
                || from.equals(to))
            throw new IllegalArgumentException();
        Request request = new Request();
        request.setFrom(from);
        request.setTo(to);
        request.setAddressToSend(getAddressToSend(from));
        request.setTokenFromAmount(new BigDecimal(fromAmount));
        request.setTokenToAmount(new BigDecimal(toAmount));
        request.setReceiverAddress(dto.getReceiverAddress());
        request.setId(UUID.randomUUID().toString());
        request.setRequestStatus(RequestStatus.CREATED);
        Calendar instance = Calendar.getInstance();
        Date creationDate = instance.getTime();
        request.setCreationDate(creationDate);
        instance.add(Calendar.MINUTE, 30);
        request.setExpiryDate(instance.getTime());
        return repository.save(request);
    }

    private String getAddressToSend(Token token) {
        return addressService.getAddressByNetwork(token.getNetwork()).getAddress();
    }

    @Override
    public Request cancel(RequestCancelDTO dto) {
        if (dto.getId() != null) {
            Request request = repository.getRequestById(dto.getId());
            if (request != null) {
                request.setRequestStatus(RequestStatus.CANCELED);
                return repository.save(request);
            } else
                return null;
        }
        return null;
    }

    private boolean checkInvalidAmount(Double amount) {
        return amount <= 0;
    }

    @Override
    public GetRequestDTO getById(String id) {
        Request request = repository.getRequestById(id);
        if (request == null)
            return null;
        return new GetRequestDTO(request);
    }

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 1)
    public void checkExpiry() {
        List<Request> expired = repository
                .findAllByExpiryDateBeforeAndRequestStatusIs(Date.from(Instant.now()), RequestStatus.CREATED)
                .stream()
                .parallel()
                .peek(request -> request.setRequestStatus(RequestStatus.EXPIRED))
                .collect(Collectors.toList());
        repository.saveAll(expired);
    }
}
