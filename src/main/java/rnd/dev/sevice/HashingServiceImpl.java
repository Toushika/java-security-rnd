package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.HashingResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class HashingServiceImpl implements HashingService {

    @Override
    public Mono<HashingResponse> hashMessage(HashingRequest hashingRequest) {

        return Mono.just(builtHashResponse(hashingRequest));
    }

    private HashingResponse builtHashResponse(HashingRequest hashingRequest) {
        return HashingResponse.builder()
                .sha256ConvertedMessage(builtSha256Message(hashingRequest.getMessage()))
                .build();
    }

    private String builtSha256Message(String plainText) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(plainText.getBytes());
            return byteToHexString(hashBytes);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String byteToHexString(byte[] hashBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) stringBuilder.append('0');
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
