package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.RsaEncryptionRequest;
import rnd.dev.dto.response.RsaEncryptionResponse;

public interface RsaEncryptionService {
    Mono<RsaEncryptionResponse> encryptMessage(RsaEncryptionRequest rsaEncryptionRequest);
}
