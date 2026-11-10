package com.example.spring.hibernate_demo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.spring.hibernate_demo.entity.Student;

/**
 * Data Access Object implementation for performing CRUD operations on the
 * {@link Student} entity using JPA and Hibernate.
 */
@Repository
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    /**
     * Constructs the DAO implementation with the injected 
     * {@link EntityManager}.
     *
     * @param em the entity manager used for database operations
     */
    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student order by lastName", Student.class);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);
        query.setParameter("theData", lastName);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery(
                "DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
