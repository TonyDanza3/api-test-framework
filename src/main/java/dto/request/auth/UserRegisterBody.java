package dto.request.auth;

public record UserRegisterBody(String email, String password, String displayName) {
}
