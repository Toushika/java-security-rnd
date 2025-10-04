package rnd.dev.sevice;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Duration;
import java.util.Base64;

@Service
public class AesSessionRedisServiceImpl implements AesSessionRedisService {
    private final ReactiveStringRedisTemplate redisTemplate;
    private static final long TTL_SECONDS = 1800; // 30 min

    public AesSessionRedisServiceImpl(ReactiveStringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<String> storeSecretKey(String sessionId, SecretKey secretKey) {
        String base64Key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        return redisTemplate.opsForValue()
                .set("session:" + sessionId, base64Key, Duration.ofSeconds(TTL_SECONDS))
                .thenReturn("Secret key has been generated");
    }

    @Override
    public Mono<SecretKey> getSecretKey(String sessionId) {
        return redisTemplate.opsForValue()
                .get("session:" + sessionId)
                .map(base64Key -> {
                    byte[] aesSecretKey = Base64.getDecoder().decode(base64Key);
                    return new SecretKeySpec(aesSecretKey, 0, aesSecretKey.length, "AES");
                });
    }

    @Override
    public Mono<Void> deleteSecretKey(String sessionId) {
        return redisTemplate.delete("session:" + sessionId).then();
    }
}
