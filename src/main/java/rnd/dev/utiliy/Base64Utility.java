package rnd.dev.utiliy;

import java.util.Base64;

public class Base64Utility {
    private Base64Utility() {
    }

    public static String encoding(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    public static String decoding(String encodedMessage) {
        return new String(Base64.getDecoder().decode(encodedMessage.getBytes()));
    }
}
