package rnd.dev.sevice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rnd.dev.dto.request.DecodingRequest;
import rnd.dev.dto.request.EncodingRequest;
import rnd.dev.dto.response.Base64DecodingResponse;
import rnd.dev.dto.response.Base64EncodingResponse;
import rnd.dev.dto.response.DecodingResponse;
import rnd.dev.dto.response.EncodingResponse;
import rnd.dev.utiliy.Base64Utility;

@Slf4j
@Service
public class CodecServiceImpl implements CodecService {
    @Override
    public <T extends EncodingResponse> EncodingResponse encode(EncodingRequest encodingRequest) {
        log.info("CodecServiceImpl :: encode :: encodingRequest : {}", encodingRequest);
        return builtEncodedResponse(encodingRequest.getMessage());
    }

    @Override
    public <T extends DecodingResponse> DecodingResponse decode(DecodingRequest decodingRequest) {
        log.info("CodecServiceImpl :: decode :: decodingRequest : {}", decodingRequest);
        return builtDecodedResponse(decodingRequest.getMessage());
    }

    private Base64EncodingResponse builtEncodedResponse(String message) {
        log.info(Base64Utility.encoding(message));
        return Base64EncodingResponse.builder()
                .encodedMessage(Base64Utility.encoding(message))
                .build();
    }

    private Base64DecodingResponse builtDecodedResponse(String message) {
        return Base64DecodingResponse.builder()
                .plainText(Base64Utility.decoding(message))
                .build();

    }
}
