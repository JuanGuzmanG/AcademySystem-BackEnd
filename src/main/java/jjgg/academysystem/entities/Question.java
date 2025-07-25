package jjgg.academysystem.entities;

import jakarta.persistence.*;

@Entity
@Table(name="questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String questionContent;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String correctOption;

    @Transient
    private String selectedAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Test test;


    public Question(){}
    public Question(Long questionId, String questionContent, String option1, String option2, String option3, String option4, String correctOption, Test test) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
        this.test = test;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long idQuestion) {
        this.questionId = idQuestion;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String contentQuestion) {
        this.questionContent = contentQuestion;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(String selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}
