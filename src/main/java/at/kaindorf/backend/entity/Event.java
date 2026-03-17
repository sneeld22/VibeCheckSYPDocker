package at.kaindorf.backend.entity;

import at.kaindorf.backend.dto.EventDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eventId")
    private long id;

    private String title;
    private String location;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventDate;
    private String imageUrl;

    @ManyToMany
    @JsonIgnoreProperties("events")
    @JoinTable(
        name = "event_artist",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> artists;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "event")
    @JsonManagedReference
    private List<Rating> ratings;

    public EventDto toDto(){
        EventDto eventDto = new EventDto();
        eventDto.setId(id);
        eventDto.setTitle(title);
        eventDto.setLocation(location);
        eventDto.setEventDate(eventDate);
        eventDto.setImageUrl(imageUrl);

        eventDto.setAverageRating(calculateAverageRating());

        return eventDto;
    }

    private float calculateAverageRating(){
        if(ratings.isEmpty()){
            return 0;
        }
        float ratingSum = 0;
        for(Rating rating : ratings){
            ratingSum += rating.getStars();
        }
        return ratingSum / ratings.size();
    }
}
