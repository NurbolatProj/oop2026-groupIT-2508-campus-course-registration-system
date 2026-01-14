package kz.aitu.services;

import kz.aitu.entities.Student;
import kz.aitu.exceptions.InvalidInputException;
import kz.aitu.repositories.StudentRepository;

import java.util.List;

public class StudentService {


    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public void addStudent(String name, int age) {
        if (age < 0)
            throw new InvalidInputException("Age must be positive");

        repo.create(new Student(name, age));
    }

    public Student getStudent(int id) {
        return repo.findById(id);
    }

    public List<Student> getAll() {
        return repo.findAll();
    }
}
