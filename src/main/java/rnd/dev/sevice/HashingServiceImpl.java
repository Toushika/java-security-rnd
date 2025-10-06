package rnd.dev.sevice;

import org.springframework.stereotype.Service;
import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.HashingResponse;
import rnd.dev.utiliy.Sha256Utility;

@Service
public class HashingServiceImpl implements HashingService {

    @Override
    public <T extends HashingResponse> HashingResponse getHashedMessage(HashingRequest hashingRequest) {
        return builtHashingResponse(hashingRequest.getMessage());
    }

    private HashingResponse builtHashingResponse(String message) {
        return HashingResponse.builder()
                .hashedMessage(Sha256Utility.hashMessage(message))
                .build();
    }
}
