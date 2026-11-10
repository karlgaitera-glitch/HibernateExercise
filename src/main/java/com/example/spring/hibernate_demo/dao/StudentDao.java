package com.example.spring.hibernate_demo.dao;

import java.util.List;

import com.example.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object (DAO) interface defining CRUD operations for managing
 * {@link Student} entities in the database.
 */
public interface StudentDao {

    /**
     * Persists a new student entity to the database.
     *
     * @param student the {@link Student} entity to save
     */
    void save(Student student);

    /**
     * Finds a student by primary key ID.
     *
     * @param id the unique ID of the student
     * @return the found {@link Student}, or {@code null} if no entity exists
     */
    Student findById(Integer id);

    /**
     * Retrieves all students from the database.
     *
     * @return a {@link List} containing all {@link Student} entities
     */
    List<Student> findAll();

    /**
     * Retrieves students matching the specified last name.
     *
     * @param lastName the last name to filter students by
     * @return a {@link List} of matching {@link Student} entities
     */
    List<Student> findByLastName(String lastName);

    /**
     * Updates an existing student entity in the database.
     *
     * @param student the {@link Student} entity with updated information
     */
    void update(Student student);

    /**
     * Deletes a student entity by primary key ID.
     *
     * @param id the unique ID of the student to remove
     */
    void delete(Integer id);

    /**
     * Deletes all student records from the database.
     *
     * @return the number of student records deleted
     */
    int deleteAll();
}
