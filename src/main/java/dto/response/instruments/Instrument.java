package dto.response.instruments;

import java.time.LocalDateTime;

public record Instrument(
        String id,
        String symbol,
        String name,
        String assetClass,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
