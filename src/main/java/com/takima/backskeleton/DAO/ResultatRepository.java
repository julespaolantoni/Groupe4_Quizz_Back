package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    @Query("SELECT r FROM Resultat r WHERE r.reponseOption.question.id = :questionId")
    List<Resultat> findAllByQuestionId(Long questionId);

    @Query("SELECT r FROM Resultat r WHERE r.reponseOption.question.sondage.id = :sondageId")
    List<Resultat> findAllBySondageId(Long sondageId);
}
