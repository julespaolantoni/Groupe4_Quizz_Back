package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.ReponseOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReponseOptionRepository extends JpaRepository<ReponseOption, Long> {

    @Query("SELECT ro FROM ReponseOption ro WHERE ro.question.id = :questionId")
    List<ReponseOption> findAllByQuestionId(@Param("questionId") Long questionId);

    @Query("SELECT ro FROM ReponseOption ro WHERE ro.question.sondage.id = :sondageId")
    List<ReponseOption> findAllBySondageId(@Param("sondageId") Long sondageId);
}
