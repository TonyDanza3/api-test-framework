package dto.response.auth;

public record UserLoginBody(String accessToken, String tokenType, Long expiresIn) {
}
