package com.takima.backskeleton.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "reponse_option")
public class ReponseOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "texte_option")
    private String texteOption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToOne(mappedBy = "reponseOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private Resultat resultat;
}
