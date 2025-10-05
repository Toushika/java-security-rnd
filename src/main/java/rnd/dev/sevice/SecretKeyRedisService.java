package rnd.dev.sevice;

import reactor.core.publisher.Mono;

public interface SecretKeyRedisService {
   Mono<String> storeSecretKey(String clientId, String secretKey);
   Mono<String> getSecretKey(String clientId);
   Mono<Void> deleteSecretKey(String clientId);

}
