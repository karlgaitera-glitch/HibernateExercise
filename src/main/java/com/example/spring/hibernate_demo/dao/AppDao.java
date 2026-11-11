package com.example.spring.hibernate_demo.dao;

import java.util.List;

import com.example.spring.hibernate_demo.entity.Course;
import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object (DAO) interface defining operations for managing 
 * {@link Instructor}, {@link InstructorDetail}, {@link Course}, and
 * {@link Student} entities.
 */
public interface AppDao {

    /**
     * Persists a new instructor along with any cascaded entities.
     *
     * @param instructor the {@link Instructor} entity to save
     */
    void save(Instructor instructor);

    /**
     * Finds an instructor by primary key ID.
     *
     * @param theId the unique ID of the instructor
     * @return the found {@link Instructor}, or {@code null} if no entity
     *         exists
     */
    Instructor findInstructorById(int theId);

    /**
     * Deletes an instructor by primary key ID while handling child
     * relationship disassociations.
     *
     * @param theId the unique ID of the instructor to remove
     */
    void deleteInstructorById(int theId);

    /**
     * Finds instructor details by primary key ID.
     *
     * @param theId the unique ID of the instructor detail record
     * @return the found {@link InstructorDetail}, or {@code null} if no entity
     *         exists
     */
    InstructorDetail findInstructorDetailById(int theId);

    /**
     * Deletes an instructor detail record by primary key ID along with its
     * associated instructor via cascade deletion. 
     * (InstructorDetail.Instructor CascadeType was ALL)
     *
     * @param theId the unique ID of the instructor detail to remove
     */
    void deleteInstructorDetail(int theId);

    /**
     * Deletes an instructor detail record by primary key ID while severing the
     * bidirectional link to leave the associated instructor intact. 
     * (InstructorDetail.Instructor CascadeType is now
     * DETACH, MERGE, PERSIST, REFRESH)
     *
     * @param theId the unique ID of the instructor detail to remove
     */
    void deleteInstructorDetailWithNull(int theId);

    /**
     * Retrieves all courses assigned to a specific instructor.
     *
     * @param theId the unique ID of the instructor
     * @return a {@link List} of {@link Course} entities associated with the
     *         given instructor ID
     */
    List<Course> findCoursesByInstructorId(int theId);

    /**
     * Finds an instructor by ID and eagerly loads their courses using a JOIN
     * FETCH query.
     *
     * @param theId the unique ID of the instructor
     * @return the {@link Instructor} populated with their courses
     */
    Instructor findInstructorByIdJoinFetch(int theId);
    
    /**
     * Updates an existing instructor's details in the database.
     *
     * @param instructor the {@link Instructor} object containing updated
     *                   fields
     */
    void updateInstructor(Instructor instructor);

    /**
     * Persists a new course entity.
     *
     * @param theCourse the {@link Course} entity to save
     */
    void save(Course theCourse);

    /**
     * Retrieves a course and eagerly fetches its associated reviews by course
     * ID.
     *
     * @param theId the unique ID of the course
     * @return the {@link Course} populated with its reviews
     */
    Course findCourseAndReviewsByCourseId(int theId);

    /**
     * Deletes a course by primary key ID.
     *
     * @param theId the unique ID of the course to remove
     */
    void deleteCourseById(int theId);

    /**
     * Retrieves a course and eagerly fetches all enrolled students by course
     * ID.
     *
     * @param theId the unique ID of the course
     * @return the {@link Course} populated with its enrolled students
     */
    Course findCourseAndStudentByCourseId(int theId);
    
    /**
     * Retrieves a student and eagerly fetches all courses they are enrolled in
     * by student ID.
     *
     * @param theId the unique ID of the student
     * @return the {@link Student} populated with their enrolled courses
     */
    Student findStudentAndCoursesByStudentId(int theId);

    /**
     * Updates an existing student entity in the database.
     *
     * @param student the {@link Student} object containing updated fields
     */
    void update(Student student);
    
    /**
     * Deletes a student by primary key ID while clearing enrollment
     * associations.
     *
     * @param theId the unique ID of the student to remove
     */
    void deleteStudentById(int theId);
}
