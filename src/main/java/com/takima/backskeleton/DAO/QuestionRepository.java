package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.options o LEFT JOIN FETCH o.resultat WHERE q.sondage.id = :sondageId")
    List<Question> findAllBySondageIdWithOptions(Long sondageId);

    @Query("SELECT DISTINCT q FROM Question q LEFT JOIN FETCH q.options o LEFT JOIN FETCH o.resultat WHERE q.id = :id")
    Optional<Question> findByIdWithOptions(Long id);
}
