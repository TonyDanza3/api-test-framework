package request.http.dto.user.response;

public record UserLoginBody(String accessToken, String tokenType, Long expiresIn) {
}
