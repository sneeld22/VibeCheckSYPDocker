package at.kaindorf.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {
    private long id;
    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;
    private float averageRating;
}
