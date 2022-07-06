package example.api.data_base.tables;

import lombok.Data;
import lombok.Setter;


@Data
public class UserProfileTable {
    @Setter
    private String id;
    private String smsNotification;
    private String pushNotification;
    private String passport;
    private String email;
    private String securityQuestion;
    private String securityAnswer;
    private String appRegistrationDate;
    private String emailSubscription;
    private String clientId;

    public UserProfileTable(){

    }
}
