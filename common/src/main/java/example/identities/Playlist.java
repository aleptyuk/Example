package example.identities;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.io.Serializable;

@Data
@Builder
public class Playlist implements Serializable {
    private String cover;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private String name;
    private String description;
    private File picture;

}