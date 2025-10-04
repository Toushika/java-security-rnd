package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AESDecryptionRequest;
import rnd.dev.dto.response.AESDecryptionResponse;

public interface AesDecryptionService {
    Mono<AESDecryptionResponse> decryptMessage(AESDecryptionRequest aesDecryptionRequest);
}
