package jjgg.academysystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name="subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSubject;

    private String nameSubject;
    private String descriptionSubject;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<Test> tests = new LinkedHashSet<>();

    public Subject() {}
    public Subject(String nameSubject, String descriptionSubject) {
        this.nameSubject = nameSubject;
        this.descriptionSubject = descriptionSubject;
    }

    public Long getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Long idSubject) {
        this.idSubject = idSubject;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getDescriptionSubject() {
        return descriptionSubject;
    }

    public void setDescriptionSubject(String descriptionSubject) {
        this.descriptionSubject = descriptionSubject;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
