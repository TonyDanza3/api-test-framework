package dto.request.auth;

import dto.request.RequestBody;

public record UserLoginBody(String email, String password) implements RequestBody {
}
