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
    private Long subjectId;

    private String subjectName;
    private String subjectDescription;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<Test> tests = new LinkedHashSet<>();

    public Subject() {}
    public Subject(String subjectName, String subjectDescription) {
        this.subjectName = subjectName;
        this.subjectDescription = subjectDescription;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long idSubject) {
        this.subjectId = idSubject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String nameSubject) {
        this.subjectName = nameSubject;
    }

    public String getSubjectDescription() {
        return subjectDescription;
    }

    public void setSubjectDescription(String descriptionSubject) {
        this.subjectDescription = descriptionSubject;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
