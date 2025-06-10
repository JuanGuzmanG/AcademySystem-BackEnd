package jjgg.academysystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tests")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTest;

    private String testName;
    private String descriptionTest;
    private String maxPoints;
    private int cantQuestions;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    private Subject subject;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy = "test",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Result> results = new HashSet<>();

    public Test(){}
    public Test(Long idTest, String testName, String descriptionTest, String maxPoints, int cantQuestions, boolean active, Subject subject, Set<Question> questions) {
        this.idTest = idTest;
        this.testName = testName;
        this.descriptionTest = descriptionTest;
        this.maxPoints = maxPoints;
        this.cantQuestions = cantQuestions;
        this.active = active;
        this.subject = subject;
        this.questions = questions;
    }

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String nameTest) {
        this.testName = nameTest;
    }

    public String getDescriptionTest() {
        return descriptionTest;
    }

    public void setDescriptionTest(String descriptionTest) {
        this.descriptionTest = descriptionTest;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }

    public int getCantQuestions() {
        return cantQuestions;
    }

    public void setCantQuestions(int cantQuestions) {
        this.cantQuestions = cantQuestions;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Result> getResults() {
        return results;
    }

    public void setResults(Set<Result> results) {
        this.results = results;
    }
}
