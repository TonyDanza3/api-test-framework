package dto.response;

public record UserLoginBody(String accessToken, String tokenType, Long expiresIn) {
}
