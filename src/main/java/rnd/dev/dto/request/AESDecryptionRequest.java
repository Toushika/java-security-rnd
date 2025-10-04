package rnd.dev.dto.request;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class AESDecryptionRequest extends EncryptionDecryptionRequest {
    private String encryptedMessage;
}
