package com.example.spring.hibernate_demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Represents a {@link Course} entity mapped to the "course" database table.
 * 
 * Manages relationships with an assigned {@link Instructor}, associated
 * {@link Review} entities, and enrolled {@link Student} entities.
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH })
    @JoinTable(
        name = "course_student", 
        joinColumns = @JoinColumn(name = "course_id"), 
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    /**
     * Default no-arg constructor required by JPA.
     */
    public Course() {
    }

    /**
     * Constructs a new Course with the specified title.
     *
     * @param title the title of the course
     */
    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", title=" + title + "]";
    }

    /**
     * Adds a review to the course's collection of reviews.
     * Initializes the review collection if it has not been instantiated yet.
     *
     * @param rev the {@link Review} entity to add to this course
     */
    public void addReview(Review rev) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(rev);
    }

    /**
     * Adds a student to the course's collection of enrolled students.
     * Initializes the students collection if it has not been instantiated yet.
     *
     * @param stud the {@link Student} entity to enroll in this course
     */
    public void addStudent(Student stud) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(stud);
    }

}
