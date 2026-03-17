package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.EventDto;
import at.kaindorf.backend.entity.Event;
import at.kaindorf.backend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Page<EventDto> findAll(Pageable pageable){
        return eventRepository.findAll(pageable)
                .map(Event::toDto);
    }

    public EventDto findById(Long id){
        return eventRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Event not found"))
                .toDto();
    }

    public Page<EventDto> findByArtistId(Long artistId, Pageable pageable){
        return eventRepository.findByArtists_Id(artistId, pageable)
                .map(Event::toDto);
    }
}
