package at.kaindorf.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private long id;
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;
}
