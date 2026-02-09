package kz.aitu.repositories;

import kz.aitu.CoreComponent.entities.Student;

public interface StudentRepository extends Repository<Student> {

    Student findById(int id);

    Integer findIdByName(String name);
}
