package rnd.dev.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.AesDecryptionRequest;
import rnd.dev.dto.request.AesEncryptionRequest;
import rnd.dev.dto.response.AesDecryptionResponse;
import rnd.dev.dto.response.AesEncryptionResponse;
import rnd.dev.sevice.AesCryptoServiceImpl;
import rnd.dev.sevice.CryptoService;

import static rnd.dev.constant.UrlConstants.AES_DECRYPTION_PATH;
import static rnd.dev.constant.UrlConstants.AES_ENCRYPTION_PATH;

@RestController
public class CryptoController extends AbstractController {

    private final CryptoService aesCryptoService;

    public CryptoController(AesCryptoServiceImpl aesCryptoService) {
        this.aesCryptoService = aesCryptoService;
    }


    @PostMapping(AES_ENCRYPTION_PATH)
    public Mono<AesEncryptionResponse> doAesEncryption(@RequestBody AesEncryptionRequest aesEncryptionRequest) {
        return aesCryptoService.encrypt(aesEncryptionRequest);
    }

    @PostMapping(AES_DECRYPTION_PATH)
    public Mono<AesDecryptionResponse> doAesDecryption(@RequestBody AesDecryptionRequest aesDecryptionRequest) {
        return aesCryptoService.decrypt(aesDecryptionRequest);
    }


}
