package rnd.dev.sevice;

import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

public interface AesSessionRedisService {
   Mono<String> storeSecretKey(String sessionId, SecretKey secretKey);
   Mono<SecretKey> getSecretKey(String sessionId);
   Mono<Void> deleteSecretKey(String sessionId);

}
