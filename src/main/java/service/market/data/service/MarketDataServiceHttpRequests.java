package service.market.data.service;

import core.http.HttpRequest;
import dto.response.instruments.Instrument;
import dto.response.instruments.Instruments;
import io.restassured.common.mapper.TypeRef;
import java.util.List;
import java.util.Map;

import static service.market.data.service.Endpoints.INSTRUMENTS_GET;

public class MarketDataServiceHttpRequests {

    public static Instruments getInstruments(String userToken) {
        Map<String, String> headers = Map.of("Authorization", "Bearer " + userToken);
        return HttpRequest.send(INSTRUMENTS_GET.getEndpoint(), headers, 200).as(Instruments.class);
    }
}