package rnd.dev.sevice;

import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.response.DecodingResponse;
import rnd.dev.dto.response.EncodingResponse;

public interface CodecService {
    <T extends EncodingResponse> EncodingResponse encode(EncodingRequest encodingRequest);

    <T extends DecodingResponse> DecodingResponse decode(DecodingRequest decodingRequest);
}
