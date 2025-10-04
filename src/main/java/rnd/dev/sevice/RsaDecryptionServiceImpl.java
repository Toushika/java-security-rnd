package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.RsaDecryptionRequest;
import rnd.dev.dto.response.RsaDecryptionResponse;
import rnd.dev.utiliy.RsaUtility;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

@Service
public class RsaDecryptionServiceImpl implements RsaDecryptionService {
    private final PrivateKey privateKey;

    public RsaDecryptionServiceImpl() {
        try {
            this.privateKey = RsaUtility.getPrivateKey();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<RsaDecryptionResponse> decryptMessage(RsaDecryptionRequest rsaDecryptionRequest) {

        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(rsaDecryptionRequest.getEncryptedMessage()));
            return Mono.just(builtRsaDecryptionResponse(decryptedBytes));
        } catch (InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }

    private static RsaDecryptionResponse builtRsaDecryptionResponse(byte[] decryptedBytes) {
        return RsaDecryptionResponse.builder()
                .originalMessage(new String(decryptedBytes, StandardCharsets.UTF_8))
                .build();
    }
}
