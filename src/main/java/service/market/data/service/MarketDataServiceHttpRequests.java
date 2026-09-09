package service.market.data.service;

import core.endpoint.Endpoint;
import core.http.HttpMethod;
import dto.request.instruments.InstrumentRequestBody;
import dto.response.instruments.InstrumentResponseBody;
import dto.response.instruments.InstrumentsResponseBody;
import java.util.Map;

import static core.http.HttpRequest.send;
import static service.market.data.service.Endpoints.INSTRUMENTS_GET;
import static service.market.data.service.Endpoints.INSTRUMENTS_POST;

public class MarketDataServiceHttpRequests {

    public static InstrumentsResponseBody getInstruments(String userToken) {
        return send(INSTRUMENTS_GET.getEndpoint(), prepareAuthHeader(userToken), 200).as(InstrumentsResponseBody.class);
    }

    public static InstrumentResponseBody getInstrumentById(String id, String userToken) {
        Map<String,String> authHeader = prepareAuthHeader(userToken);
        Endpoint byIdEndpoint = new Endpoint(HttpMethod.GET, INSTRUMENTS_GET.getEndpoint().url() + "/" + id);
        return send(byIdEndpoint, authHeader, 200).as(InstrumentResponseBody.class);
    }

    public static InstrumentResponseBody addInstrument(InstrumentRequestBody instrument, String userToken) {
         return send(INSTRUMENTS_POST.getEndpoint(),prepareAuthHeader(userToken), instrument, 201)
                .as(InstrumentResponseBody.class);

    }

    private static Map<String, String> prepareAuthHeader(String userToken) {
        return Map.of("Authorization", "Bearer " + userToken);
    }
}