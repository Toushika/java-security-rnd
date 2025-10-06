package rnd.dev.sevice;

import rnd.dev.dto.request.HashingRequest;
import rnd.dev.dto.response.HashingResponse;

public interface HashingService {
    <T extends HashingResponse> HashingResponse getHashedMessage(HashingRequest hashingRequest);
}
