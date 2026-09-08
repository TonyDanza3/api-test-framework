package extension.utils;

import java.util.Random;

public class ExtentionUtils {
    private static final Random random = new Random();

    public static String generateRandomString() {
        StringBuilder randomStringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            randomStringBuilder.append((char)random.nextInt('a','z'));
        }
        return randomStringBuilder.toString();
    }
}
