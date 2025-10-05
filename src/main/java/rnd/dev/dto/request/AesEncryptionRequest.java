package rnd.dev.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AesEncryptionRequest extends EncryptionDecryptionRequest{
    private String message;
}
