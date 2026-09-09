package test;

import core.http.HttpRequest;
import dto.request.auth.UserRegisterBody;
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
import dto.response.auth.UserLoginBody;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static core.http.HttpRequest.send;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static service.auth.service.AuthServiceHttpRequests.loginForUser;
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

    @ParameterizedTest
    @MethodSource("userSource")
    public void registerInvalidUser(String email, String userPassord, String userName) {
        HttpRequest.send(REGISTER.getEndpoint(), new UserRegisterBody(email, userPassord, userName), 400);
    }

    @Test
    public void loginWithInvalidPassword(@User dto.User user) {
        send(LOGIN.getEndpoint(), new dto.request.auth.UserLoginBody(user.login(), "invalidPasswd"), 401);
    }

    @Test
    public void loginWithInvalidLogin(@User dto.User user) {
        send(LOGIN.getEndpoint(), new dto.request.auth.UserLoginBody("invalidLogin", user.password()), 400);
    }

    public static Stream<Arguments> userSource() {
        return Stream.of(
                arguments("one", "two", ""),
                arguments("one", "", "two"),
                arguments("", "one", "two"),
                arguments("one",  "two", null),
                arguments("one",  null, "two"),
                arguments(null,  "two", "one")
        );
    }
}