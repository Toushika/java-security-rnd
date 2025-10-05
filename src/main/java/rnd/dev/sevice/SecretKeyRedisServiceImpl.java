package rnd.dev.sevice;

import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class SecretKeyRedisServiceImpl extends AbstractRedisService implements SecretKeyRedisService {
    private static final long TTL_SECONDS = 1800; // 30 min

    public SecretKeyRedisServiceImpl(ReactiveStringRedisTemplate redisTemplate) {
        super(redisTemplate);
    }

    @Override
    public Mono<String> storeSecretKey(String clientId, String secretKey) {
        return save("clientId:" + clientId, secretKey, Duration.ofSeconds(TTL_SECONDS))
                .thenReturn(secretKey);

    }

    @Override
    public Mono<String> getSecretKey(String clientId) {
        return get("clientId:" + clientId, String.class);
    }

    @Override
    public Mono<Void> deleteSecretKey(String clientId) {
        return delete("clientId:" + clientId).then();
    }
}
