package extension;

import dto.User;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static extension.utils.ExtensionUtils.generateRandomString;
import static service.auth.service.AuthServiceHttpRequests.registerUser;

public class CreateAndRegisterUserExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == User.class;
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        String email = generateRandomString() + "@gmail.com";
        String password = generateRandomString();
        String userName = generateRandomString();
        registerUser(email, password, userName);
        return new User(email, password);

    }
}
