package example;

import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
public abstract class BaseEntity implements Serializable {
    protected Integer id;
}
