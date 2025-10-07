package com.takima.backskeleton.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "resultat")
public class Resultat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_votes")
    private Integer nombreVotes = 0;

    @OneToOne
    @JoinColumn(name = "reponse_id")
    private ReponseOption reponseOption;
}
