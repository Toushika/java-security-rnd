package rnd.dev.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class AesDecryptionRequest extends AesEncryptionDecryptionRequest {
    private String encryptedMessage;
}
