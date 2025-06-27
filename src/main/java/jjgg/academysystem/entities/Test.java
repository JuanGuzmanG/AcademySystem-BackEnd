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
    private Long testId;

    private String testName;
    private String testDescription;
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
    public Test(Long testId, String testName, String testDescription, String maxPoints, int cantQuestions, boolean active, Subject subject, Set<Question> questions) {
        this.testId = testId;
        this.testName = testName;
        this.testDescription = testDescription;
        this.maxPoints = maxPoints;
        this.cantQuestions = cantQuestions;
        this.active = active;
        this.subject = subject;
        this.questions = questions;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long idTest) {
        this.testId = idTest;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String nameTest) {
        this.testName = nameTest;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String descriptionTest) {
        this.testDescription = descriptionTest;
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

    @Override
    public String toString() {
        return "Test{" +
                "idTest=" + testId +
                ", testName='" + testName + '\'' +
                ", descriptionTest='" + testDescription + '\'' +
                ", maxPoints='" + maxPoints + '\'' +
                ", cantQuestions=" + cantQuestions +
                ", active=" + active +
                ", subject=" + subject +
                ", questions=" + questions +
                ", results=" + results +
                '}';
    }
}
