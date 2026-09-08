package test;

import extension.EmailExtension;
import extension.RandomStringExtension;
import extension.utils.RandomEmail;
import extension.utils.RandomString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dto.response.UserLoginBody;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static service.auth.service.AuthService.loginForUser;
import static service.auth.service.AuthService.registerUser;

@ExtendWith({EmailExtension.class, RandomStringExtension.class})
public class AuthServiceTest {

    @Test
    public void ableToLoginForRegisteredUser(@RandomEmail String email, @RandomString String password, @RandomString String userName) {
        registerUser(email,password, userName);
        UserLoginBody user =  loginForUser(email, password).getBody().as(UserLoginBody.class);
        assertTrue(!user.accessToken().isEmpty());
    }
}