package extension;

import dto.response.auth.UserLoginBody;
import extension.utils.UserToken;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static extension.utils.ExtensionUtils.generateRandomString;
import static service.auth.service.AuthServiceHttpRequests.loginForUser;
import static service.auth.service.AuthServiceHttpRequests.registerUser;

public class UserTokenExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(UserToken.class);
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String email = generateRandomString() + "@gmail.com";
        String password = generateRandomString();
        String userName = generateRandomString();
        registerUser(email, password, userName);
        return loginForUser(email, password).getBody().as(UserLoginBody.class).accessToken();

    }
}
