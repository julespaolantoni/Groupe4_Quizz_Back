package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SondageRepository extends JpaRepository<Sondage, Long> {
    @Query("SELECT DISTINCT s FROM Sondage s LEFT JOIN FETCH s.questions q LEFT JOIN FETCH q.options o LEFT JOIN FETCH o.resultat")
    List<Sondage> findAllWithDetails();

    @Query("SELECT DISTINCT s FROM Sondage s LEFT JOIN FETCH s.questions q LEFT JOIN FETCH q.options o LEFT JOIN FETCH o.resultat WHERE s.id = :id")
    Optional<Sondage> findByIdWithDetails(Long id);
}
