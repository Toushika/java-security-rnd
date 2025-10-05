package rnd.dev.sevice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecryptionRequest;
import rnd.dev.dto.request.EncryptionRequest;
import rnd.dev.dto.response.AesDecryptionResponse;
import rnd.dev.dto.response.AesEncryptionResponse;
import rnd.dev.dto.response.DecryptionResponse;
import rnd.dev.dto.response.EncryptionResponse;
import rnd.dev.utiliy.AesUtility;

@Slf4j
@Service
public class AesCryptoServiceImpl implements CryptoService {

    private final SecretKeyRedisService secretKeyRedisService;

    public AesCryptoServiceImpl(SecretKeyRedisService secretKeyRedisService) {
        this.secretKeyRedisService = secretKeyRedisService;
    }

    @Override
    public <T extends EncryptionResponse> Mono<T> encrypt(EncryptionRequest encryptionRequest) {
        return getAesKey(encryptionRequest)
                .map(aesKeyString -> AesUtility.encrypt(encryptionRequest.getMessage(), aesKeyString))
                .map(this::builtEncryptionResponse)
                .cast((Class<T>) AesEncryptionResponse.class); // Cast to generic T
    }

    @Override
    public <T extends DecryptionResponse> Mono<T> decrypt(DecryptionRequest decryptionRequest) {
        log.info("AesCryptoServiceImpl :: decrypt :: request : {}", decryptionRequest);
        return secretKeyRedisService.getSecretKey(decryptionRequest.getClientId())
                .doOnNext(aesKeyString -> log.info("AesCryptoServiceImpl :: decrypt :: aesKeyString : {}", aesKeyString))
                .map(aesKeyString -> AesUtility.decrypt(decryptionRequest.getEncryptedMessage(), aesKeyString))
                .map(this::builtDecryptionResponse)
                .doOnNext(decryptedMessage ->  log.info("AesCryptoServiceImpl :: decrypt :: decryptedMessage : {}", decryptedMessage))
                .cast((Class<T>) AesDecryptionResponse.class);
    }

    private Mono<String> getAesKey(EncryptionRequest encryptionRequest) {
        return secretKeyRedisService.getSecretKey(encryptionRequest.getClientId())
                .switchIfEmpty(Mono.defer(() -> secretKeyRedisService.storeSecretKey(encryptionRequest.getClientId(),
                        AesUtility.generateSecretKey())));
    }

    private EncryptionResponse builtEncryptionResponse(String encryptedMessage) {
        return AesEncryptionResponse.builder()
                .encryptedMessage(encryptedMessage)
                .build();
    }

    private DecryptionResponse builtDecryptionResponse(String decryptedMessage) {
        return AesDecryptionResponse.builder()
                .plainText(decryptedMessage)
                .build();
    }
}
