package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AesDecryptionRequest;
import rnd.dev.dto.response.AeSDecryptionResponse;

public interface AesDecryptionService {
    Mono<AeSDecryptionResponse> decryptMessage(AesDecryptionRequest aesDecryptionRequest);
}
