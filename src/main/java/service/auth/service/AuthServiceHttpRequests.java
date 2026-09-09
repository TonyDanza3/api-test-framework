package service.auth.service;

import dto.response.auth.UserLoginBody;
import io.restassured.response.Response;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dto.request.auth.UserRegisterBody;

import static core.http.HttpRequest.send;
import static service.auth.service.Endpoints.LOGIN;
import static service.auth.service.Endpoints.REGISTER;

public class AuthServiceHttpRequests {
    private static final Logger LOGGER = LogManager.getLogger();

    public static Response registerUser(String email, String password, String username) {
        return send(REGISTER.getEndpoint(), new UserRegisterBody(email, password, username), 201);
    }

    public static Response loginForUser(String email, String password) {
        Response response = send(LOGIN.getEndpoint(), new dto.request.auth.UserLoginBody(email, password), 200);
        UserLoginBody user = response.getBody().as(UserLoginBody.class);
        LOGGER.log(Level.INFO, "Successfully logged user and got his access token " + user.accessToken());
        return response;
    }
}