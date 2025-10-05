package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AesEncryptionRequest;
import rnd.dev.dto.response.AesEncryptionResponse;

public interface AesEncryptionService {
    Mono<AesEncryptionResponse> encryptMessage(AesEncryptionRequest aesEncryptionRequest);
}
