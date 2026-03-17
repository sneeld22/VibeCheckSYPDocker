package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.RatingDto;
import at.kaindorf.backend.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<RatingDto>> getRatingsByEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(ratingService.findByEventId(eventId));
    }

    @PostMapping("/{eventId}")
    public ResponseEntity<?> createRating(
            @PathVariable Long eventId,
            @RequestBody RatingDto ratingDto
    ) {
        RatingDto createdRatingDto;
        try {
            createdRatingDto = ratingService.addRatingToEvent(eventId, ratingDto);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No event with id");
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Rating must be between 1 and 5");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdRatingDto);
    }
}
