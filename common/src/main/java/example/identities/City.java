package example.identities;


import example.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class City extends BaseEntity {
    private String cityName;
    private Country country;
}
