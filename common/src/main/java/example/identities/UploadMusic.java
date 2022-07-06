package example.identities;


import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.io.Serializable;

@Data
@Builder

public class UploadMusic implements Serializable{
    private String author;
    private String title;
    private String album;
    private String genre;
    private File cover;
}
