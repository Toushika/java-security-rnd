package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.response.DecodingResponse;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
public class DecodingServiceImpl implements DecodingService {
    @Override
    public Mono<DecodingResponse> decodeMessage(DecodingRequest decodingRequest) {
        return Mono.just(builtDecodeResponse(decodingRequest.getEncodedMessage()));
    }

    private DecodingResponse builtDecodeResponse(String encodedMessage) {
        return DecodingResponse.builder()
                .originalMessage(decodeToBase64(encodedMessage))
                .build();

    }

    private String decodeToBase64(String encodedMessage) {
        byte[] decodeBytes = Base64.getDecoder().decode(encodedMessage);
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }
}
