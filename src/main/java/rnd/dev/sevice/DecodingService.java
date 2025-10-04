package rnd.dev.sevice;

import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.response.DecodingResponse;

public interface DecodingService {
    Mono<DecodingResponse> decodeMessage(DecodingRequest decodingRequest);
}
