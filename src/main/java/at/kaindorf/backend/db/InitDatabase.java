package at.kaindorf.backend.db;

import at.kaindorf.backend.entity.Artist;
import at.kaindorf.backend.entity.Event;
import at.kaindorf.backend.repository.ArtistRepository;
import at.kaindorf.backend.repository.EventRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InitDatabase {
    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    private void init(){
        objectMapper.registerModule(new JavaTimeModule());
        loadArtists();
        loadEvents();
    }

    private void loadArtists(){
        try {
            InputStream inputStream = getClass().getResourceAsStream("/vibes_artists.json");
            List<Artist> artists = objectMapper.readerForListOf(Artist.class).readValue(inputStream);
            artistRepository.saveAll(artists);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private void loadEvents() {
        try {
            InputStream inputStream = getClass().getResourceAsStream("/vibes_events.json");
            List<Event> events = objectMapper.readerForListOf(Event.class).readValue(inputStream);

            for(Event event : events){
                List<Artist> managedArtists = new ArrayList<>();
                for (Artist artist : event.getArtists()) {
                    Artist managedArtist = artistRepository
                            .findArtistByFirstNameAndLastName(artist.getFirstName(), artist.getLastName())
                            .orElseThrow(() -> new RuntimeException("Artist not found: " + artist.getFirstName() + " " + artist.getLastName()));
                    managedArtists.add(managedArtist);
                }
                event.setArtists(managedArtists);
            }

            eventRepository.saveAll(events);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
