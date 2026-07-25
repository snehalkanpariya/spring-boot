package com.academic.taskmanager.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

import org.springframework.cglib.core.Local;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String subject;
    private LocalDate deadLine;
    private boolean completed;


    public Task(){

    }
    public Task(String title,String subject,LocalDate deadLine,boolean completed){
        this.title=title;
        this.subject=subject;
        this.deadLine=deadLine;
        this.completed=completed;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public String getSubject(){
        return subject;
    }
    public void setSubject(String subject){
        this.subject=subject;
    }
    public LocalDate getDeadLine(){
        return deadLine;
    }
    public void setDeadLine(LocalDate deadLine){
        this.deadLine=deadLine;
    }
    public boolean isCompleted(){
        return completed;
    }
    public void setCompleted(boolean completed){
        this.completed=completed;
    }
    
}
