package com.example.spring.hibernate_demo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.spring.hibernate_demo.entity.Course;
import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object implementation for handling database operations on
 * entities such as Instructor, InstructorDetail, Course, and Student using
 * JPA/Hibernate.
 */
@Repository 
public class AppDaoImplement implements AppDao {

    private EntityManager em;

    /**
     * Constructs the DAO implementation with the given {@link EntityManager}.
     *
     * @param em the entity manager used to perform database operations
     */
    public AppDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Instructor findInstructorById(int theId) {
        return em.find(Instructor.class, theId);
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return em.find(InstructorDetail.class, theId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail instructorDetail = em.find(
            InstructorDetail.class, theId);
        em.remove(instructorDetail);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteInstructorDetailWithNull(int theId) {
        InstructorDetail instructorDetail = em.find(
            InstructorDetail.class, theId);
        instructorDetail.getInstructor().setInstructorDetail(null);
        em.remove(instructorDetail);
    }    

    /**
     * {@inheritDoc}
     */
    @Override     
    public List<Course> findCoursesByInstructorId(int theId) {
        TypedQuery<Course> query = em.createQuery(
            "FROM Course WHERE instructor.id=:data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();
        return courses;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = em.createQuery(
            "SELECT i FROM Instructor i JOIN FETCH i.courses WHERE i.id=:data",
             Instructor.class);
        query.setParameter("data", theId);

        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    @Transactional
    public void updateInstructor(Instructor instructor) {
        em.merge(instructor);
    }   

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional 
    public void save(Course theCourse){
        em.persist(theCourse);
    }

    /**
     * {@inheritDoc}
     */
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void deleteCourseById(int theId){
        Course course = em.find(Course.class, theId);
        em.remove(course);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Course findCourseAndStudentByCourseId(int theId){
        TypedQuery<Course> query = em.createQuery(
            "SELECT c FROM Course c "
            + "JOIN FETCH c.students "
            + "WHERE c.id=:data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();
        return course;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student> query = em.createQuery(
            "SELECT s FROM Student s "
            + "JOIN FETCH s.courses "
            + "WHERE s.id=:data", Student.class);
        query.setParameter("data", theId);
        
        Student student = query.getSingleResult();
        return student;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Student student){
        em.merge(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override 
    @Transactional 
    public void deleteStudentById(int theId){
        Student student = em.find(Student.class, theId);

        if (student != null){
            List<Course> courses = student.getCourses();
            for (Course c: courses){
                c.getStudents().remove(student);
            }
        }

        em.remove(student);
    }
}
