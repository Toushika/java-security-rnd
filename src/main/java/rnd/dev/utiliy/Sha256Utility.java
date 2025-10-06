package rnd.dev.utiliy;

import rnd.dev.error.exception.Sha256Exception;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Sha256Utility {
    private Sha256Utility() {
    }

    public static String hashMessage(String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] messageBytes = messageDigest.digest(message.getBytes());
            return getHexOutput(messageBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new Sha256Exception(e);
        }
    }

    private static String getHexOutput(byte[] messageBytes) {
        return HexFormat.of().formatHex(messageBytes);
    }
}
