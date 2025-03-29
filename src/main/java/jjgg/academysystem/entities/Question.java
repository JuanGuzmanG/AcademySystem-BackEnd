package jjgg.academysystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idQuestion;

    @Column(length = 5000)
    private String contentQuestion;

    private String imageQuestion;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;

    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;
}
