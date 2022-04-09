package com.example.myappforuniversity;

public class NoteModel {

    private String noteTitle;
    private  String noteContent;
    private String noteTime;
    public NoteModel() {

    }
    public NoteModel(String noteTitle, String noteTime,String noteContent) {
        this.noteTitle = noteTitle;
         this.noteContent=noteContent; this.noteTime = noteTime;
    }

    public String getNoteTime() {
        return noteTime;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public void setNoteTime(String noteTime) {
        this.noteTime = noteTime;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}


