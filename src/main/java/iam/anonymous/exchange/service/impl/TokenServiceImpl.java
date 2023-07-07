package iam.anonymous.exchange.service.impl;

import iam.anonymous.exchange.domain.Token;
import iam.anonymous.exchange.repository.TokenRepository;
import iam.anonymous.exchange.service.RateService;
import iam.anonymous.exchange.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {
    private final TokenRepository repository;
    private final RateService rateService;

    @Autowired
    public TokenServiceImpl(TokenRepository repository, RateService rateService) {
        this.repository = repository;
        this.rateService = rateService;
    }

    @Override
    public Collection<Token> getAll() {
        return repository.findAll();
    }

    @Override
    public Token getById(Long id) {
        return repository.getById(id);
    }

    @Override
    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void updatePrices() {
        List<Token> updated = getAll()
                .stream()
                .parallel()
                .filter(token -> !token.getAbbreviation().equals("USDT"))
                .peek(token -> {
                    Map.Entry<Double, Double> priceAnd24HDifference = rateService.getPriceAnd24HDifference(token.getAbbreviation());
                    double newPrice = BigDecimal.valueOf(priceAnd24HDifference.getKey() * 0.98D).setScale(token.getDecimals(), RoundingMode.DOWN).doubleValue();
                    token.setDifference(BigDecimal.valueOf(priceAnd24HDifference.getValue()).setScale(2, RoundingMode.CEILING).doubleValue());
                    token.setPrice(newPrice);
                })
                .collect(Collectors.toList());
        repository.saveAll(updated);
    }
}
