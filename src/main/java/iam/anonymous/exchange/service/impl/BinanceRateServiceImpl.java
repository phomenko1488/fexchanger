package iam.anonymous.exchange.service.impl;

import iam.anonymous.exchange.dto.BinanceDTO;
import iam.anonymous.exchange.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class BinanceRateServiceImpl implements RateService {
    private final static String API_URL = "https://api.binance.us/api/v3/ticker/24hr?symbol=%sUSDT";
    private final RestTemplate restTemplate;

    @Autowired
    public BinanceRateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Map.Entry<Double, Double> getPriceAnd24HDifference(String abbreviation) {
        String url = String.format(API_URL, abbreviation);
        try {

            ResponseEntity<BinanceDTO> binanceDTOResponseEntity = restTemplate.getForEntity(url, BinanceDTO.class);
            if (binanceDTOResponseEntity.getBody() != null && binanceDTOResponseEntity.getBody().getLastPrice() != null && binanceDTOResponseEntity.getBody().getPriceChangePercent() != null)
                return Map.entry(binanceDTOResponseEntity.getBody().getLastPrice(), binanceDTOResponseEntity.getBody().getPriceChangePercent());
            else
                return null;
        }catch (Exception e){
            System.out.println(url);
            return null;
        }
    }
}
