package test;

import core.http.HttpRequest;
import dto.request.UserRegisterBody;
import extension.CreateAndRegisterUserExtension;
import extension.EmailExtension;
import extension.RandomStringExtension;
import extension.utils.RandomEmail;
import extension.utils.RandomString;
import extension.utils.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dto.response.UserLoginBody;

import static core.http.HttpRequest.send;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static service.auth.service.AuthService.loginForUser;
import static service.auth.service.Endpoints.LOGIN;
import static service.auth.service.Endpoints.REGISTER;

@ExtendWith({CreateAndRegisterUserExtension.class, EmailExtension.class, RandomStringExtension.class})
public class AuthServiceTest {

    @Test
    public void registerNewUser(@RandomEmail String email, @RandomString String password, @RandomString String userName) {
        Response resp = send(REGISTER.getEndpoint(), new UserRegisterBody(email, password, userName), 201);
        Assertions.assertEquals(201, resp.statusCode());
    }

    @Test
    public void ableToLoginForRegisteredUser(@User dto.User user) {
        UserLoginBody userLoginResponse = loginForUser(user.login(), user.password()).getBody().as(UserLoginBody.class);
        assertFalse(userLoginResponse.accessToken().isEmpty());
    }

    @Test
    public void loginWithInvalidPassword(@User dto.User user) {
        send(LOGIN.getEndpoint(),  new dto.request.UserLoginBody(user.login(), "invalidPasswd"), 401);
    }

    @Test
    public void loginWithInvalidLogin(@User dto.User user) {
        send(LOGIN.getEndpoint(),  new dto.request.UserLoginBody("invalidLogin", user.password()), 400);
    }
}