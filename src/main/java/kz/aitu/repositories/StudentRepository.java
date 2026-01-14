package kz.aitu.repositories;

import kz.aitu.entities.Student;
import java.util.List;

public interface   StudentRepository {
    void create(Student student);
    Student findById(int id);
    List<Student> findAll();
}

