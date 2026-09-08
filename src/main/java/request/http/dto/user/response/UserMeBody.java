package request.http.dto.user.response;

import java.time.LocalDateTime;

public record UserMeBody(
        String id,
        String email,
        String displayName,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}