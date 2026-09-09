package dto.response.instruments;

import java.time.OffsetDateTime;

public record InstrumentResponseBody(
        String id,
        String symbol,
        String name,
        String assetClass,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
