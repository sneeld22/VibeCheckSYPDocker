package at.kaindorf.backend.repository;

import at.kaindorf.backend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByEvent_Id(Long eventId);
}
