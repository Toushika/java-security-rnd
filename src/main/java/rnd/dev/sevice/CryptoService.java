package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecryptionRequest;
import rnd.dev.dto.request.EncryptionRequest;
import rnd.dev.dto.response.DecryptionResponse;
import rnd.dev.dto.response.EncryptionResponse;

public interface CryptoService {

    <T extends EncryptionResponse> Mono<T> encrypt(EncryptionRequest encryptionRequest);

    <T extends DecryptionResponse> Mono<T> decrypt(DecryptionRequest decryptionRequest);
}
