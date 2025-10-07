package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.ReponseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReponseOptionRepository extends JpaRepository<ReponseOption, Long> {
    @Query("SELECT DISTINCT ro FROM ReponseOption ro LEFT JOIN FETCH ro.resultat WHERE ro.question.id = :questionId")
    List<ReponseOption> findAllByQuestionIdWithResultat(Long questionId);

    @Query("SELECT DISTINCT ro FROM ReponseOption ro LEFT JOIN FETCH ro.resultat WHERE ro.question.sondage.id = :sondageId")
    List<ReponseOption> findAllBySondageIdWithResultat(Long sondageId);

    @Query("SELECT DISTINCT ro FROM ReponseOption ro LEFT JOIN FETCH ro.resultat WHERE ro.id = :id")
    Optional<ReponseOption> findByIdWithResultat(Long id);
}
