package rnd.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.response.DecodingResponse;
import rnd.dev.dto.response.EncodingResponse;
import rnd.dev.sevice.CodecService;

import static rnd.dev.constant.UrlConstants.DECODE_PATH;
import static rnd.dev.constant.UrlConstants.ENCODE_PATH;

@RestController
public class CodecController extends AbstractController {

    private final CodecService codecService;

    public CodecController(CodecService codecService) {
        this.codecService = codecService;
    }

    @PostMapping(ENCODE_PATH)
    public Mono<EncodingResponse> encodeMessage(@RequestBody EncodingRequest encodingRequest) {
        return Mono.just(codecService.encode(encodingRequest));
    }

    @PostMapping(DECODE_PATH)
    public Mono<DecodingResponse> decodeMessage(@RequestBody DecodingRequest decodingRequest) {
        return Mono.just(codecService.decode(decodingRequest));
    }
}
