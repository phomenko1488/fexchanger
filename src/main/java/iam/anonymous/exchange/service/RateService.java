package iam.anonymous.exchange.service;

import java.util.Map;

public interface RateService {
    Map.Entry<Double, Double> getPriceAnd24HDifference(String abbreviation);
}
