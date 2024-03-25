package com.example.assignment_02;

public class Question {

    public String option1;
    public String option2;
    public String option3;
    public String option4;
    public String correctAnswer;
    public int imageResourceId; // Add image resource ID
    int score = 0;
    boolean answered = false;

    public Question(String op1, String op2, String op3, String op4, String correct, int imageResourceId) {
        this.option1 = op1;
        this.option2 = op2;
        this.option3 = op3;
        this.option4 = op4;
        this.correctAnswer = correct;
        this.imageResourceId = imageResourceId;
    }
}
