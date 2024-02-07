package com.example.learn.database.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.learn.database.entity.*;

@Repository
public class AppDaoImplementation implements AppDAO {

    private EntityManager entityManager;

    @Autowired
    public AppDaoImplementation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public InstructorDetails findDetailsById(int id) {
        return entityManager.find(InstructorDetails.class, id);
    }

    @Override
    public Instructor findInstructor(String email) {
        TypedQuery<Instructor> instructor = entityManager.createQuery("FROM Instructor where email=:email",
                Instructor.class);

        instructor.setParameter("email", email);

        return instructor.getResultList().get(0);
    }

    @Override
    public List<Student> findAll() {
    TypedQuery<Student> query = entityManager.createQuery("FROM Student",
    Student.class);
    return query.getResultList();

    }

    @Override
    @Transactional
    public void updateStudent(int id) {
    Student student = entityManager.find(Student.class, id);
    student.setFirstName("New name");
    entityManager.merge(student);

    // Query query = entityManager.createQuery("UPDATE Student SET
    // lastName='lastname' WHERE id=:id",
    // Student.class);
    // query.setParameter("id", id);
    // query.executeUpdate();
    }

}
