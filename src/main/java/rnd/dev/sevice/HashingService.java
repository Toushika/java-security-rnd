package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.HashingResponse;

public interface HashingService {
    Mono<HashingResponse> hashMessage(HashingRequest hashingRequest);
}
