package test;

import core.http.HttpRequest;
import extension.CreateAndRegisterUserExtension;
import extension.utils.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dto.response.UserLoginBody;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static service.auth.service.AuthService.loginForUser;
import static service.auth.service.Endpoints.LOGIN;

@ExtendWith(CreateAndRegisterUserExtension.class)
public class AuthServiceTest {

    @Test
    public void ableToLoginForRegisteredUser(@User dto.User user) {
        UserLoginBody userLoginResponse = loginForUser(user.login(), user.password()).getBody().as(UserLoginBody.class);
        assertFalse(userLoginResponse.accessToken().isEmpty());
    }

    @Test
    public void loginWithInvalidPassword(@User dto.User user) {
        HttpRequest.send(LOGIN.getEndpoint(),  new dto.request.UserLoginBody(user.login(), "invalidPasswd"), 401);
    }
}