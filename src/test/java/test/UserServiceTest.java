package test;

import extension.EmailExtension;
import extension.RandomStringExtension;
import extension.utils.RandomEmail;
import extension.utils.RandomString;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dto.response.UserLoginBody;
import dto.response.UserMeBody;
import service.user.service.UserService;

import static service.auth.service.AuthService.loginForUser;
import static service.auth.service.AuthService.registerUser;

@ExtendWith({EmailExtension.class, RandomStringExtension.class})
public class UserServiceTest {

    @Test
    public void ableToGetInfoAboutLoggedUser(@RandomEmail String email, @RandomString String password, @RandomString String userName) {
        registerUser(email,password, userName);
        String authToken = loginForUser(email, password).getBody().as(UserLoginBody.class).accessToken();
        UserMeBody user = UserService.getUserInfo(authToken).getBody().as(UserMeBody.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(!user.id().isEmpty());
        assertions.assertThat(user.email().equals(email));
        assertions.assertThat(user.displayName().equals(userName));
        assertions.assertAll();
    }
}
