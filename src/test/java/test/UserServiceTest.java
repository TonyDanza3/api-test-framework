package test;

import extension.EmailExtension;
import extension.RandomStringExtension;
import extension.utils.RandomEmail;
import extension.utils.RandomString;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import dto.response.auth.UserLoginBody;
import dto.response.user.UserMeBody;
import service.user.service.UserService;

import static service.auth.service.AuthServiceHttpRequests.loginForUser;
import static service.auth.service.AuthServiceHttpRequests.registerUser;

@ExtendWith({EmailExtension.class, RandomStringExtension.class})
public class UserServiceTest {

    @Test
    public void ableToGetInfoAboutLoggedUser(@RandomEmail String email, @RandomString String password, @RandomString String userName) {
        registerUser(email,password, userName);
        String authToken = loginForUser(email, password).getBody().as(UserLoginBody.class).accessToken();
        UserMeBody userInfoFromUserMe = UserService.getUserInfo(authToken).getBody().as(UserMeBody.class);
        SoftAssertions assertions = new SoftAssertions();
        assertions.assertThat(!userInfoFromUserMe.id().isEmpty());
        assertions.assertThat(userInfoFromUserMe.email().equals(email));
        assertions.assertThat(userInfoFromUserMe.displayName().equals(userName));
        assertions.assertAll();
    }
}
