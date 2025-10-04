package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.response.EncodingResponse;

public interface EncodingService {
    Mono<EncodingResponse> encodeMessage(EncodingRequest encodingRequest);
}
