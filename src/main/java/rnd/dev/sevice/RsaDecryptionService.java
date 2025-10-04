package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.RsaDecryptionRequest;
import rnd.dev.dto.response.RsaDecryptionResponse;

public interface RsaDecryptionService {
    Mono<RsaDecryptionResponse> decryptMessage(RsaDecryptionRequest rsaDecryptionRequest);
}
