package rnd.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.HashingResponse;
import rnd.dev.sevice.HashingService;

import static rnd.dev.constant.UrlConstants.HASHING_PATH;

@RestController
public class HashingController extends AbstractController {
    private final HashingService hashingService;

    public HashingController(HashingService hashingService) {
        this.hashingService = hashingService;
    }

    @PostMapping(HASHING_PATH)
    public Mono<HashingResponse> hashMessage(@RequestBody HashingRequest hashingRequest) {
        return Mono.just(hashingService.getHashedMessage(hashingRequest));
    }
}

