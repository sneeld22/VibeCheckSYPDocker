package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.EventDto;
import at.kaindorf.backend.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Page<EventDto>> getAllEvents(
            @PageableDefault(size = 5, sort = "eventDate") Pageable pageable
    ) {
        System.out.println("Test");
        return ResponseEntity.ok(eventService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id){
        EventDto eventDto;
        try {
            eventDto = eventService.findById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No event with id");
        }

        return ResponseEntity.ok(eventDto);
    }

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<Page<EventDto>> getEventsByArtistId(
            @PathVariable Long artistId,
            @PageableDefault(size = 5, sort = "eventDate") Pageable pageable
    ){
        return ResponseEntity.ok(eventService.findByArtistId(artistId, pageable));
    }
}
