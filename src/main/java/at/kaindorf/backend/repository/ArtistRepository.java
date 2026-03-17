package at.kaindorf.backend.repository;

import at.kaindorf.backend.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
    Optional<Artist> findArtistByFirstNameAndLastName(String firstName, String lastName);
}
