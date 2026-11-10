package com.example.spring.hibernate_demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents an {@link Instructor} entity mapped to the "instructor" table.
 * 
 * Manages relationships with detailed profile information in
 * {@link InstructorDetail} and associated {@link Course} entities.
 */
@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany(mappedBy = "instructor", cascade = { CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH }, fetch = FetchType.LAZY)
    private java.util.List<Course> courses;
    
    /**
     * Default no-arg constructor required by JPA.
     */
    public Instructor() {
    }

    /**
     * Constructs a new Instructor with personal details.
     *
     * @param firstName the instructor's first name
     * @param lastName the instructor's last name
     * @param email the instructor's email address
     */
    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public java.util.List<Course> getCourses() {
        return courses;
    }

    public void setCourses(java.util.List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Instructor [id=" + id + ", firstName=" + firstName +
                ", lastName=" + lastName + ", email=" + email +
                ", instructorDetail=" + instructorDetail + "]";
    }

    /**
     * Adds a course to the instructor's list of courses and sets
     * this instructor as the course's instructor to maintain the
     * bidirectional relationship.
     * Initializes the courses collection if it has not been instantiated yet.
     *
     * @param tcourse the {@link Course} entity to assign to this instructor
     */
    public void addCourse(Course tcourse) {
        if (this.courses == null) {
            this.courses = new java.util.ArrayList<>();
        }
        this.courses.add(tcourse);
        tcourse.setInstructor(this);
    }
}
