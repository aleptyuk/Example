package example.api.identities.authentication;

import example.BaseEntity;
import lombok.Data;

@Data
public class AuthToken extends BaseEntity {

    private String accessToken;
    private String refreshToken;
}
