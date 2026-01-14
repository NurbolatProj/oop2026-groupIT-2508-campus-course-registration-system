package kz.aitu.repositories;

import kz.aitu.entities.Course;
import java.util.List;

public interface    CourseRepository {
    void create(Course course);
    List<Course> findAll();
}

