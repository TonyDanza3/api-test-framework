package service.user.service;

import io.restassured.response.Response;

import java.util.HashMap;

import static core.http.HttpRequest.send;
import static service.user.service.Endpoints.ME;

public class UserServiceHttpRequests {
    public static Response getUserInfo(String authToken) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + authToken);
        return send(ME.getEndpoint(), headers, 200);
    }
}