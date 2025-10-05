package rnd.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.DecodingResponse;
import rnd.dev.dto.response.EncodingResponse;
import rnd.dev.dto.response.HashingResponse;
import rnd.dev.sevice.DecodingService;
import rnd.dev.sevice.EncodingService;
import rnd.dev.sevice.HashingService;

import static rnd.dev.constant.UrlConstants.*;

@RestController
public class OperationController extends AbstractController {

    private final EncodingService encodingService;
    private final HashingService hashingService;
    private final DecodingService decodingService;

    public OperationController(EncodingService encodingService, HashingService hashingService, DecodingService decodingService) {
        this.encodingService = encodingService;
        this.hashingService = hashingService;
        this.decodingService = decodingService;
    }

    @PostMapping(ENCODE_PATH)
    public Mono<EncodingResponse> encodeMessage(@RequestBody EncodingRequest encodingRequest) {
        return encodingService.encodeMessage(encodingRequest);
    }

    @PostMapping(DECODE_PATH)
    public Mono<DecodingResponse> decodeMessage(@RequestBody DecodingRequest decodingRequest) {
        return decodingService.decodeMessage(decodingRequest);
    }

    @PostMapping(HASHING_PATH)
    public Mono<HashingResponse> hashMessage(@RequestBody HashingRequest hashingRequest) {
        return hashingService.hashMessage(hashingRequest);

    }

}

