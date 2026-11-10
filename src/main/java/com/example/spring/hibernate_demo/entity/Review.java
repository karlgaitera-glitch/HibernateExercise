package com.example.spring.hibernate_demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a {@link Review} entity mapped to the "review" database table.
 * 
 * Stores user feedback and comments associated with a {@link Course}.
 */
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comments")
    private String comments;

    /**
     * Default no-arg constructor required by JPA.
     */
    public Review() {
    }

    /**
     * Constructs a new Review with the specified comment feedback.
     *
     * @param comments text content of the review
     */
    public Review(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review [id=" + id + ", comments=" + comments + "]";
    }

    
}
