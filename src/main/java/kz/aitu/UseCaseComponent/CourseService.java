package kz.aitu.UseCaseComponent;

import kz.aitu.CoreComponent.entities.Course;
import kz.aitu.CoreComponent.entities.Result;
import kz.aitu.BuilderComponent.CourseFactory;
import kz.aitu.repositories.CourseRepository;

import java.util.List;

public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public void addCourse(
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
        repo.create(course);
    }

    public Result<Course> addCourseWithResult(
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

        repo.create(course);
        return Result.success(course);
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
