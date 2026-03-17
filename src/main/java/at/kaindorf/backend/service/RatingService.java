package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.RatingDto;
import at.kaindorf.backend.entity.Event;
import at.kaindorf.backend.entity.Rating;
import at.kaindorf.backend.repository.EventRepository;
import at.kaindorf.backend.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final EventRepository eventRepository;

    public List<RatingDto> findByEventId(Long eventId) {
        return ratingRepository.findByEvent_Id(eventId)
                .stream()
                .map(Rating::toDto)
                .toList();
    }

    public RatingDto addRatingToEvent(Long eventId, RatingDto ratingDto) {

        if (ratingDto.getStars() < 1 || ratingDto.getStars() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5 stars");
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new NoSuchElementException("Event not found with id: " + eventId)
                );


        Rating rating = new Rating();
        rating.setStars(ratingDto.getStars());
        rating.setComment(ratingDto.getComment());
        rating.setCreatedAt(LocalDateTime.now());
        rating.setEvent(event);

        Rating savedRating = ratingRepository.save(rating);

        return savedRating.toDto();
    }
}
