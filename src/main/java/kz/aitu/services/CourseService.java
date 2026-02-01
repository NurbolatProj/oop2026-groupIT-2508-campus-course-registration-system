package kz.aitu.services;

import kz.aitu.entities.Course;
import kz.aitu.factory.CourseFactory;
import kz.aitu.repositories.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public boolean addCourse(
            String type,
            String title,
            int credits,
            Integer instructorId,
            Integer classroomId
    ) {
        Course course = CourseFactory.createCourse(
                type,
                title,
                credits,
                instructorId,
                classroomId
        );

        return repo.create(course);
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public List<Course> getHighCreditCourses(int minCredits) {
        return repo.findAll()
                .stream()
                .filter(c -> c.getCredits() >= minCredits)
                .toList();
    }

}
