package dto.request.auth;

import dto.request.RequestBody;

public record UserRegisterBody(String email, String password, String displayName) implements RequestBody {
}
