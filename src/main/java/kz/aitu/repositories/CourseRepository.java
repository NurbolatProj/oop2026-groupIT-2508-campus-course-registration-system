package kz.aitu.repositories;

import kz.aitu.entities.Course;

public interface CourseRepository extends Repository<Course> {

    Integer findIdByTitle(String title);

}

