package dto.response.instruments;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public record Instrument(
        String id,
        String symbol,
        String name,
        String assetClass,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt
) {
}
