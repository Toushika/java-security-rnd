package rnd.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.sevice.AesSessionRedisService;
import rnd.dev.utiliy.AesUtility;

import javax.crypto.SecretKey;

import static rnd.dev.constant.UrlConstants.SECRET_KEY_PATH;
import static rnd.dev.constant.UrlConstants.SESSION_BASE_PATH;

@RequestMapping(SESSION_BASE_PATH)
@RestController
public class AesSessionController {
    private final AesSessionRedisService aesSessionRedisService;

    public AesSessionController(AesSessionRedisService aesSessionRedisService) {
        this.aesSessionRedisService = aesSessionRedisService;
    }

    @GetMapping(SECRET_KEY_PATH)
    public Mono<String> generateSecretKey(@RequestParam String sessionId) {
        SecretKey secretKey = AesUtility.generateSecretKey();
        return aesSessionRedisService.storeSecretKey(sessionId, secretKey);
    }
}
