package rnd.dev.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class AESEncryptionRequest extends EncryptionDecryptionRequest{
    private String message;
}
