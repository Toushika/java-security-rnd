package rnd.dev.sevice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.response.EncodingResponse;

import java.util.Base64;

@Slf4j
@Service
public class EncodingServiceImpl implements EncodingService {
    @Override
    public Mono<EncodingResponse> encodeMessage(EncodingRequest encodingRequest) {
        return Mono.just(buildEncodedResponse(encodingRequest));
    }

    private EncodingResponse buildEncodedResponse(EncodingRequest encodingRequest) {
        return EncodingResponse.builder()
                .encodedMessage(encodeToBase64(encodingRequest.getMessage()))
                .build();
    }

    private String encodeToBase64(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes());
    }
}
