package com.example.spring.hibernate_demo.dao;

import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Course;

import java.util.List;

public interface AppDao {
    void save(Instructor instructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void updateInstructor(Instructor instructor);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

    void deleteCourseById(int theId);
}
