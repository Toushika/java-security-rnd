package rnd.dev.controller;

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
    private final RsaEncryptionService rsaEncryptionService;
    private final RsaDecryptionService rsaDecryptionService;

    public OperationController(EncodingService encodingService, HashingService hashingService, DecodingService decodingService, AesEncryptionService aesEncryptionService, AesDecryptionService aesDecryptionService, RsaEncryptionService rsaEncryptionService, RsaDecryptionService rsaDecryptionService) {
        this.encodingService = encodingService;
        this.hashingService = hashingService;
        this.decodingService = decodingService;
        this.aesEncryptionService = aesEncryptionService;
        this.aesDecryptionService = aesDecryptionService;
        this.rsaEncryptionService = rsaEncryptionService;
        this.rsaDecryptionService = rsaDecryptionService;
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
    public Mono<AesEncryptionResponse> encryptMessageByAes(@RequestBody AesEncryptionRequest aesEncryptionRequest) {
        return aesEncryptionService.encryptMessage(aesEncryptionRequest);
    }

    @PostMapping(AES_DECRYPTION_PATH)
    public Mono<AeSDecryptionResponse> decryptMessageByAes(@RequestBody AesDecryptionRequest aesDecryptionRequest) {
        return aesDecryptionService.decryptMessage(aesDecryptionRequest);
    }

    @PostMapping(RSA_ENCRYPTION_PATH)
    public Mono<RsaEncryptionResponse> encryptMessageByRsa(@RequestBody RsaEncryptionRequest rsaEncryptionRequest) {
        return rsaEncryptionService.encryptMessage(rsaEncryptionRequest);
    }

    @PostMapping(RSA_DECRYPTION_PATH)
    public Mono<RsaDecryptionResponse> decryptMessageByRsa(@RequestBody RsaDecryptionRequest rsaDecryptionRequest) {
        return rsaDecryptionService.decryptMessage(rsaDecryptionRequest);
    }
}

