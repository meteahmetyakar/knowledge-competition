package com.example.knocomy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Questiondatabase {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("AnswerTrue")
    @Expose
    private String answerTrue;
    @SerializedName("Answer1")
    @Expose
    private String answer1;
    @SerializedName("Answer2")
    @Expose
    private String answer2;
    @SerializedName("Answer3")
    @Expose
    private String answer3;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public String getSubject() {
        return subject;
    }

    public String getAnswerTrue() {
        return answerTrue;
    }

    public String getAnswer1() {
        return answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

}
