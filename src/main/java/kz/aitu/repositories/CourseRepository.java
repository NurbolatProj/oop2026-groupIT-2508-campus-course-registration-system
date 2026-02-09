package kz.aitu.repositories;

import kz.aitu.CoreComponent.entities.Course;

public interface CourseRepository extends Repository<Course> {

    Integer findIdByTitle(String title);

}

