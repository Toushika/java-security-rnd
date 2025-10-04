package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.RsaEncryptionRequest;
import rnd.dev.dto.response.RsaEncryptionResponse;
import rnd.dev.utiliy.RsaUtility;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Base64;

@Service
public class RsaEncryptionServiceImpl implements RsaEncryptionService {
    private final PublicKey publicKey;

    public RsaEncryptionServiceImpl() {
        try {
            this.publicKey = RsaUtility.getPublicKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<RsaEncryptionResponse> encryptMessage(RsaEncryptionRequest rsaEncryptionRequest) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(rsaEncryptionRequest.getMessage().getBytes(StandardCharsets.UTF_8));
            return Mono.just(builtRsaResponse(encryptedBytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException |
                 InvalidKeyException e) {
            throw new RuntimeException(e);
        }

    }

    private static RsaEncryptionResponse builtRsaResponse(byte[] encryptedBytes) {
        return RsaEncryptionResponse.builder()
                .encryptedMessage(Base64.getEncoder().encodeToString(encryptedBytes))
                .build();
    }
}
