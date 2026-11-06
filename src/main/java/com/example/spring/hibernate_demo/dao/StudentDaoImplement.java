package com.example.spring.hibernate_demo.dao;

import com.example.spring.hibernate_demo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.persistence.TypedQuery;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImplement implements StudentDao {

    private EntityManager em;

    public StudentDaoImplement(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        em.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student order by lastName", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);
        query.setParameter("theData", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        em.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRows = em.createQuery(
                "DELETE FROM Student").executeUpdate();
        return numRows;
    }
}
