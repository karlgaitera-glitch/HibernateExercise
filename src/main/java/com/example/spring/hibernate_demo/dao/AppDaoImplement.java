package com.example.spring.hibernate_demo.dao;

import org.springframework.stereotype.Repository;

import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Course;

import java.util.List;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.EntityManager;

import jakarta.transaction.Transactional;

@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override 
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = em.find(Instructor.class, theId);
        List<Course> courses = instructor.getCourses();
        for(Course c: courses){
            c.setInstructor(null);
        }
        em.remove(instructor);        
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /*
    @Override
    @Transactional
    public void deleteInstructorDetailById(Integer theId) {
        InstructorDetail instructorDetail = em.find(
            InstructorDetail.class, theId);
        em.remove(instructorDetail);
    } */


    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        InstructorDetail instructorDetail = em.find(
            InstructorDetail.class, theId);
        instructorDetail.getInstructor().setInstructorDetail(null);
        em.remove(instructorDetail);
    }    

    @Override     
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    @Override 
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery(
            "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id=:data",
             Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override 
    @Transactional
    public void updateInstructor(Instructor instructor) {
        em.merge(instructor);
    }   

    @Override
    @Transactional 
    public void save(Course theCourse){
        em.persist(theCourse);
    }

    @Override
    @Transactional
    public Course findCourseAndReviewsByCourseId(int theId){
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c "
            + "JOIN FETCH c.review "
            + "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }
    
    @Override
    @Transactional
    public void deleteCourseById(int theId){
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }
}
