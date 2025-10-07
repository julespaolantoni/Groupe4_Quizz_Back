package com.takima.backskeleton.DAO;

import com.takima.backskeleton.models.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    // Les méthodes de base de JpaRepository sont suffisantes pour notre cas
}
