package rnd.dev.utiliy;

import rnd.dev.error.exception.AesEncryptionException;
import rnd.dev.error.exception.SecretKeyGenerationException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AesUtility {
    public static final String ALGORITHM_AES = "AES";
    public static final int KEY_SIZE = 128;
    public static final int SECRET_KEY_LENGTH = 16;
    private static final Cipher cipher;

    static {
        try {
            cipher = Cipher.getInstance(ALGORITHM_AES);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private AesUtility(Cipher cipher) {
    }

    public static String generateSecretKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
            keyGenerator.init(KEY_SIZE);
            return secretKeyToString(keyGenerator.generateKey());

        } catch (NoSuchAlgorithmException e) {
            throw new SecretKeyGenerationException(e.getMessage());
        }
    }


    public static String encrypt(String message, String aesKey) {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, builtSecretKey(aesKey));
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new AesEncryptionException(e.getMessage());
        }
    }


    public static String decrypt(String encryptedString, String aesKey) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, builtSecretKey(aesKey));
            byte[] decodedMessageBytes = Base64.getDecoder().decode(encryptedString);
            return new String(cipher.doFinal(decodedMessageBytes));

        } catch (InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    public static SecretKey builtSecretKey(String aesKey) {
        byte[] keyBytes = aesKey.getBytes(StandardCharsets.UTF_8);
        keyBytes = Arrays.copyOf(keyBytes, SECRET_KEY_LENGTH); // pad or truncate to 16 bytes
        return new SecretKeySpec(keyBytes, ALGORITHM_AES);
    }

    public static String secretKeyToString(SecretKey secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }
}
