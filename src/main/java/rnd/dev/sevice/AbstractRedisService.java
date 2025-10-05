package rnd.dev.sevice;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;
import rnd.dev.utiliy.MapperUtils;

import java.time.Duration;

public class AbstractRedisService {

    private final ReactiveRedisTemplate<String, String> reactiveRedisTemplate;

    public AbstractRedisService(ReactiveRedisTemplate<String, String> reactiveRedisTemplate) {
        this.reactiveRedisTemplate = reactiveRedisTemplate;
    }

    public <T> Mono<T> get(String hashKey, Class<T> tClass) {
        // implementation here
        return reactiveRedisTemplate.opsForValue()
                .get(hashKey)
                .map(value -> MapperUtils.deserialize(value, tClass));

    }

    public Mono<Boolean> save(String hashKey, Object object, Duration ttl) {
        return reactiveRedisTemplate.opsForValue()
                .set(hashKey, MapperUtils.serialize(object), ttl)
                .thenReturn(Boolean.TRUE);
    }

    public Mono<Void> delete(String hashKey) {
        return reactiveRedisTemplate.delete(hashKey).then();
    }

}
