package at.kaindorf.backend.repository;

import at.kaindorf.backend.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    Page<Event> findByArtists_Id(Long id, Pageable pageable);
}
