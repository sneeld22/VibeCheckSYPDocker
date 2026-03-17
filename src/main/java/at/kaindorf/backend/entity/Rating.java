package at.kaindorf.backend.entity;

import at.kaindorf.backend.dto.RatingDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ratingId")
    private long id;

    private int stars;
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "eventId")
    @JsonBackReference
    private Event event;

    public RatingDto toDto(){
        RatingDto ratingDto = new RatingDto();
        ratingDto.setStars(stars);
        ratingDto.setComment(comment);
        ratingDto.setCreatedAt(createdAt);

        return ratingDto;
    }
}
