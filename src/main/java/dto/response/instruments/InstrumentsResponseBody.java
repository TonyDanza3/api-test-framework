package dto.response.instruments;

import java.util.List;

public record InstrumentsResponseBody(
        List<InstrumentResponseBody> items,
        Long page,
        Long pageSize,
        Long total
) {
}
