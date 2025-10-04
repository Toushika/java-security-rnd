package rnd.dev.sevice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AESEncryptionRequest;
import rnd.dev.dto.response.AESEncryptionResponse;
import rnd.dev.utiliy.AesUtility;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Service
public class AesEncryptionServiceImpl implements AesEncryptionService {
    private final AesSessionRedisService aesSessionRedisService;

    public AesEncryptionServiceImpl(AesSessionRedisService aesSessionRedisService) {
        this.aesSessionRedisService = aesSessionRedisService;
    }

    @Override
    public Mono<AESEncryptionResponse> encryptMessage(AESEncryptionRequest aesEncryptionRequest) {
        return aesSessionRedisService.getSecretKey(aesEncryptionRequest.getSessionId())
                .switchIfEmpty(Mono.error(new RuntimeException("Session Key not found")))
                .map(secretKey -> {
                    String encryptedMessage = builtEncryptedMessage(aesEncryptionRequest.getMessage(), secretKey);
                    return builtAesResponse(encryptedMessage);
                });
    }


    private AESEncryptionResponse builtAesResponse(String encryptedMessage) {
        return AESEncryptionResponse.builder()
                .encryptedMessage(encryptedMessage)
                .build();
    }


    private String builtEncryptedMessage(String message, SecretKey secretKey) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
