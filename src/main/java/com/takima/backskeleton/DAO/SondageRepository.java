package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SondageRepository extends JpaRepository<Sondage, Long> {

    // On charge uniquement les questions ici
    @Query("SELECT DISTINCT s FROM Sondage s LEFT JOIN FETCH s.questions")
    List<Sondage> findAllWithQuestions();

    @Query("SELECT DISTINCT s FROM Sondage s LEFT JOIN FETCH s.questions WHERE s.id = :id")
    Optional<Sondage> findByIdWithQuestions(Long id);
}
