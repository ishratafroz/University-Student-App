package com.example.myappforuniversity;

public class Class1 {

    String subject;
    String link;

    public Class1() {
    }

    public Class1(String subject, String link) {
        this.subject = subject;
        this.link = link;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}