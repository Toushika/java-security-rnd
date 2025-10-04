package rnd.dev.dto.response;

import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class AESEncryptionResponse extends EncryptionResponse {
}
