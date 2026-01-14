package kz.aitu.services;

import kz.aitu.entities.Course;
import kz.aitu.repositories.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository repo;


    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public void addCourse(String title, int credits) {
        repo.create(new Course(title, credits));
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }
}
