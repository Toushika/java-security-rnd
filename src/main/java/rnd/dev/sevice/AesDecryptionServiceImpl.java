package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AesDecryptionRequest;
import rnd.dev.dto.response.AeSDecryptionResponse;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class AesDecryptionServiceImpl implements AesDecryptionService {
    private final AesSessionRedisService aesSessionRedisService;

    public AesDecryptionServiceImpl(AesSessionRedisService aesSessionRedisService) {
        this.aesSessionRedisService = aesSessionRedisService;
    }

    @Override
    public Mono<AeSDecryptionResponse> decryptMessage(AesDecryptionRequest aesDecryptionRequest) {
        return builtDecryptedMessage(aesDecryptionRequest);
    }

    private Mono<AeSDecryptionResponse> builtDecryptedMessage(AesDecryptionRequest aesDecryptionRequest) {
        return aesSessionRedisService.getSecretKey(aesDecryptionRequest.getSessionId())
                .switchIfEmpty(Mono.error(new RuntimeException("SessionKey not found")))
                .map(secretKey -> {
                    try {
                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKey);
                        byte[] decodedMessageBytes = Base64.getDecoder().decode(aesDecryptionRequest.getEncryptedMessage());
                        String originalMessage = new String(cipher.doFinal(decodedMessageBytes));
                        return builtAesDecryptionResponse(originalMessage);

                    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException |
                             IllegalBlockSizeException | BadPaddingException e) {
                        throw new RuntimeException(e);
                    }
                });

    }


    private AeSDecryptionResponse builtAesDecryptionResponse(String originalMessage) {
        return AeSDecryptionResponse.builder()
                .originalMessage(originalMessage)
                .build();
    }
}
