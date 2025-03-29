package jjgg.academysystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTest;

    private String nameTest;
    private String descriptionTest;
    private String maxPoints;
    private int cantQuestions;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();
}
