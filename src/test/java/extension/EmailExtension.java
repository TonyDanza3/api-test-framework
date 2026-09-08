package extension;

import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.Random;

public class EmailExtension implements ParameterResolver {

    private final Random random = new Random();

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == String.class;
    }

    @Override
    public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return generateRandomString() + "@gmail.com";
    }


    private String generateRandomString() {
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            randomStringBuilder.append((char)random.nextInt('a','z'));
        }
        return randomStringBuilder.toString();
    }
}
