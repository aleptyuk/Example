package example.identities;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.apache.commons.lang3.StringUtils;

@Data
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends BaseEntity {
    protected Integer id;
    @Builder.Default
    private String username = StringUtils.EMPTY;
    @JsonProperty("password")
    @Builder.Default
    private String pass = StringUtils.EMPTY;
    @Builder.Default
    private String phone = StringUtils.EMPTY;
    @Builder.Default
    private String email = StringUtils.EMPTY;
    @Builder.Default
    private String firstName = StringUtils.EMPTY;
    @Builder.Default
    private String lastName = StringUtils.EMPTY;

    private Country country;
    private String avatarName;
    private String modifiedAvatarName;
    private String dateOfBirth;
    private City city;
    private String userSubscription;
    private String registrationMethod;

}
