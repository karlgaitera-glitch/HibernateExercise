package com.example.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents an {@link InstructorDetail} entity mapped to the
 * "instructor_detail" database table.
 * 
 * Stores additional details like subject area and hobby, linked to an
 * {@link Instructor} entity.
 */
@Entity
@Table(name = "instructor_detail")
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne(mappedBy = "instructorDetail", cascade = {CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})    
    private Instructor instructor;
    
    /**
     * Default no-arg constructor required by JPA.
     */
    public InstructorDetail() {
    }

    /**
     * Constructs a new InstructorDetail with the specified subject and hobby.
     *
     * @param subject the main subject area taught by the instructor
     * @param hobby the instructor's personal hobby
     */
    public InstructorDetail(String subject, String hobby) {
        this.subject = subject;
        this.hobby = hobby;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    @Override
    public String toString() {
        return "InstructorDetail [id=" + id + ", subject=" + subject +
                ", hobby=" + hobby + "]";
    }
}
