package at.kaindorf.backend.controller;

import at.kaindorf.backend.dto.ArtistDto;
import at.kaindorf.backend.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping
    public ResponseEntity<List<ArtistDto>> getArtists(){
        return ResponseEntity.ok(artistService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistById(@PathVariable Long id){
        ArtistDto artistDto;
        try {
            artistDto = artistService.findById(id);
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No artist with id");
        }

        return ResponseEntity.ok(artistDto);
    }
}
