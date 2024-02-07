package com.example.learn.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.learn.database.entity.*;

public interface AppRepository extends JpaRepository<Student, Integer> {

}
