package rnd.dev.utiliy;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RsaUtility {

    private RsaUtility() {
    }

    public static final String PRIVATE_KEY_PEM =
            "-----BEGIN PRIVATE KEY-----\n" +
                    "MIIEugIBADANBgkqhkiG9w0BAQEFAASCBKQwggSgAgEAAoIBAQCKUhg4PUJnjcjD\n" +
                    "kVw3QoY8sFaDI8IdHALpKAcNcDnVVrJd0NoLhg7R1O7fYAXomJ/yQJ+CQXZyUvuo\n" +
                    "2iTpBY56uQxhMUx5CRGA98Hi2fuFZDe9qeXAnxVO/4Mgj8QHAsUNewkDRVytCH91\n" +
                    "0nm8i0InfF+usQOKHi99SESojJq2UHfQEsoqLk76ZqiVg+KjOrgU6Kjh5A0exrfg\n" +
                    "aXdyY0YqgfJgt8RklF3FntPSleIoB8kf6OXgLHNaFB/ZCyvsD6rqTL/uV8vAmVtq\n" +
                    "UYp0JuQyaxYxOp+JtJzwqPze3mdFdvsTW8xy9r39Yh3vPk1IWg2zjU9DBEGRu2tN\n" +
                    "eWvCW8JRAgMBAAECgf8DPelTaBwmUSxjaQTARs7enUNpz2y9QerPjCCeMAMq/N0x\n" +
                    "VbPGkV3HjTYMBjQdP3aANwOGCgK8qzU6v+IdlAPC3JNts7oXHB9M5oGkz3cVqUOl\n" +
                    "LlY0LYZSU6q/GJ9/ipETv852RSnm5EwYdtZjlN/4DPIxJfTvR8XVKdxyFtmJbFCP\n" +
                    "YnYWoRxf/9c7FvOg7N+rV1chcez/jLp0wprLGZzrh9fAHVYsg0qpuIb36AmhOTtm\n" +
                    "iICC8kf4jilqZT3PwF0v0jsW0/+inKxX4tckOIXGX9g6YJCO8v7P/sPsFWHw99RS\n" +
                    "ufL81bnabN8jEwkOHovlqQ29rskrzEhwwVlgoAECgYEAwg5By/39tW3rOj+JjfMm\n" +
                    "rMy7R0CpIa37lwVGyNE1WPmYW+fnlauBNxegA5cfh77T2tl+TXecxn5Gq6NkQ0l/\n" +
                    "fP/X8MosOy5awQjlVZJkgNpy4/bFjEK0aZMaTgiMTQCSqZLD999cHnBKi+oqZFjI\n" +
                    "b3SRvQ77+fUH2v5QRv06P1ECgYEAtnlQFKhSxAfHjy5H4LVT8ems+CpjLdyBdfD1\n" +
                    "bFhx7FVztSVMd79/SdghXX5LbjNkflBuwSrDMGLHysiK382IsI04Uvb9J3gxcZ7k\n" +
                    "Xl8mDjGzGIZTQLETPYigslzFGTXuGmjhi3Im14ajT1y3OL5NSDYoVmnxzdkgMM2H\n" +
                    "dHDmkwECgYBBJewtAf5VFKvuCDqz7zfCt5EAINMnPXPDhb/311SjpFfkMvzqoINm\n" +
                    "7YWI7wlk10nkpTPj6g42DVrIcLhOZrRoULQ8YINs8ukA+W2Uxur631vG8KftqM9M\n" +
                    "ylgAbpo7l7+lMmKcKt6QUX6Vbm4A9lWEJYxv/kHV7s9OVCa7TnRpoQKBgD5ASLej\n" +
                    "bQj/dm88ZL0eX1MKWz4Wh9tffLMVz0R1L/hmdqD35ox7whsZJtjUeztPQRtuqTox\n" +
                    "MoHRUUO3AtmzLsy8gNic9mR+tq+Ce3pOj5cMEYNuX/yT7HJzqA7xYzRkl6Re34NJ\n" +
                    "IEMq5Y/vm3o6Qfc+sqg4YIvPZRIBLBLnaWIBAoGAMw1yYVd5O6lUx2CLp0/5GKB7\n" +
                    "hUmXXY47dwb9CR85MK15VGhj9LedxrCG0JJMSxk9B9jufd8LAlpQlu1u2sKZ2U2P\n" +
                    "vr1UaQA/JFWo+F1Y+qjCwU0QbeHbj+TzU3MbSDxrZ2UpMkDF9mGeZmfLPxUtQkmd\n" +
                    "Uhvw2T+uYMSu/z73084=\n" +
                    "-----END PRIVATE KEY-----";


    public static final String PUBLIC_KEY_PEM =
            "-----BEGIN PUBLIC KEY-----\n" +
                    "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAilIYOD1CZ43Iw5FcN0KG\n" +
                    "PLBWgyPCHRwC6SgHDXA51VayXdDaC4YO0dTu32AF6Jif8kCfgkF2clL7qNok6QWO\n" +
                    "erkMYTFMeQkRgPfB4tn7hWQ3vanlwJ8VTv+DII/EBwLFDXsJA0VcrQh/ddJ5vItC\n" +
                    "J3xfrrEDih4vfUhEqIyatlB30BLKKi5O+maolYPiozq4FOio4eQNHsa34Gl3cmNG\n" +
                    "KoHyYLfEZJRdxZ7T0pXiKAfJH+jl4CxzWhQf2Qsr7A+q6ky/7lfLwJlbalGKdCbk\n" +
                    "MmsWMTqfibSc8Kj83t5nRXb7E1vMcva9/WId7z5NSFoNs41PQwRBkbtrTXlrwlvC\n" +
                    "UQIDAQAB\n" +
                    "-----END PUBLIC KEY-----";



    // Convert PEM to PrivateKey
    public static PrivateKey getPrivateKey() throws Exception {
        String privateKeyPEM = PRIVATE_KEY_PEM
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    // Convert PEM to PublicKey
    public static PublicKey getPublicKey() throws Exception {
        String publicKeyPEM = PUBLIC_KEY_PEM
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] keyBytes = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

}
