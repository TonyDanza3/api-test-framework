package dto.response.instruments;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Instruments(
        List<Instrument> items,
        Long page,
        Long pageSize,
        Long total
) {
}
