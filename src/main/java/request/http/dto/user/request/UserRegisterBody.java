package request.http.dto.user.request;

public record UserRegisterBody(String email, String password, String displayName) {
}
