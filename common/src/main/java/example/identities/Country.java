package example.identities;


import example.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Country extends BaseEntity {

    private String countryName;

    public Country(String countryName) {
        this.countryName = countryName;
    }
}
