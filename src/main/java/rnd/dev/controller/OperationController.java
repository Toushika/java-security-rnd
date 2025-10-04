package rnd.dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.*;
import rnd.dev.dto.response.*;
import rnd.dev.sevice.*;

import static rnd.dev.constant.UrlConstants.*;

@RestController
public class OperationController extends AbstractController {
    private final EncodingService encodingService;
    private final HashingService hashingService;
    private final DecodingService decodingService;
    private final AesEncryptionService aesEncryptionService;
    private final AesDecryptionService aesDecryptionService;

    public OperationController(EncodingService encodingService, HashingService hashingService, DecodingService decodingService, AesEncryptionService aesEncryptionService, AesDecryptionService aesDecryptionService) {
        this.encodingService = encodingService;
        this.hashingService = hashingService;
        this.decodingService = decodingService;
        this.aesEncryptionService = aesEncryptionService;
        this.aesDecryptionService = aesDecryptionService;
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

    @PostMapping(AES_ENCRYPTION_PATH)
    public Mono<AESEncryptionResponse> encryptMessageByAes(@RequestBody AESEncryptionRequest aesEncryptionRequest) {
        return aesEncryptionService.encryptMessage(aesEncryptionRequest);
    }

    @PostMapping(AES_DECRYPTION_PATH)
    public Mono<AESDecryptionResponse> decryptMessageByAes(@RequestBody AESDecryptionRequest aesDecryptionRequest) {
        return aesDecryptionService.decryptMessage(aesDecryptionRequest);
    }
}

