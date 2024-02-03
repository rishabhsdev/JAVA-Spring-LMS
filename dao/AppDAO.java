package com.example.learn.database.dao;

import java.util.List;

import com.example.learn.database.entity.*;

public interface AppDAO {

    void save(Instructor instructor);

    Instructor findInstructor(String email);

    // List<Instructor> findAll();
    Instructor findInstructorById(int id);

    InstructorDetails findDetailsById(int id);

    // void updateStudent(int id);
}
