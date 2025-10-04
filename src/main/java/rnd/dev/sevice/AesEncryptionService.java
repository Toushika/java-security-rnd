package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AESEncryptionRequest;
import rnd.dev.dto.response.AESEncryptionResponse;

public interface AesEncryptionService {
    Mono<AESEncryptionResponse> encryptMessage(AESEncryptionRequest aesEncryptionRequest);
}
