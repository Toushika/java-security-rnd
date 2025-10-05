package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AesDecryptionRequest;
import rnd.dev.dto.response.AesDecryptionResponse;

public interface AesDecryptionService {
    Mono<AesDecryptionResponse> decryptMessage(AesDecryptionRequest aesDecryptionRequest);
}
