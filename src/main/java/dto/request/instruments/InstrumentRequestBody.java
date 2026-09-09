package dto.request.instruments;

import dto.request.RequestBody;

public record InstrumentRequestBody(String symbol, String name, String assetClass) implements RequestBody {

}