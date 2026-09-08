package service.auth.service;

import dto.response.UserLoginBody;
import io.restassured.response.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dto.request.UserRegisterBody;

import static core.http.HttpRequest.send;
import static service.auth.service.Endpoints.LOGIN;
import static service.auth.service.Endpoints.REGISTER;

public class AuthService {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Response registerUser(String email, String password, String username) {
        return send(REGISTER.getEndpoint(), new UserRegisterBody(email, password, username));
    }

    public static Response loginForUser(String email, String password) {
        Response response = send(LOGIN.getEndpoint(), new dto.request.UserLoginBody(email, password));
        UserLoginBody user = response.getBody().as(UserLoginBody.class);
        LOGGER.log(Level.INFO, "Successfully logged user and got his access token " + user.accessToken());
        return response;
    }
}