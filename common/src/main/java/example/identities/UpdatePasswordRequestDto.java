package example.identities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.StringUtils;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePasswordRequestDto {
    @Builder.Default
    private String newPassword = StringUtils.EMPTY;
}
