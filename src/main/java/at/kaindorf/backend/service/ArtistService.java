package at.kaindorf.backend.service;

import at.kaindorf.backend.dto.ArtistDto;
import at.kaindorf.backend.entity.Artist;
import at.kaindorf.backend.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public List<ArtistDto> findAll(){
        return artistRepository.findAll()
                .stream()
                .map(Artist::toDto)
                .toList();
    }

    public ArtistDto findById(Long id){
        return artistRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Artist not found"))
                .toDto();
    }
}
