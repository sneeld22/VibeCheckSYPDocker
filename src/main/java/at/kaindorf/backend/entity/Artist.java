package at.kaindorf.backend.entity;

import at.kaindorf.backend.dto.ArtistDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artistId")
    private long id;

    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

    @ManyToMany
    @JsonIgnoreProperties("artists")
    private List<Event> events;

    public Artist(String line) {
        String[] tokens = line.split(" ");
        if(tokens.length != 2) {
            return;
        }

        this.firstName = tokens[0];
        this.lastName = tokens[1];
    }

    public ArtistDto toDto(){
        ArtistDto artistDto = new ArtistDto();
        artistDto.setId(id);
        artistDto.setFirstName(firstName);
        artistDto.setLastName(lastName);
        artistDto.setDescription(description);
        artistDto.setImageUrl(imageUrl);

        return artistDto;
    }
}
