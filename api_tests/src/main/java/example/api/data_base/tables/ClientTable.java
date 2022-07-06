package example.api.data_base.tables;

import lombok.Data;
import lombok.Setter;

@Data
public class ClientTable {
    @Setter
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String countryOfResidence;
    private String passportDataId;
    private String accessionDate;
    private String clientStatus;
    private String mobilePhone;
    private String employerIdentificationNumber;

    public ClientTable(){

    }
}
