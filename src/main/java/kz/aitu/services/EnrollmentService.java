package kz.aitu.services;

import kz.aitu.entities.Enrollment;
import kz.aitu.entities.Student;
import kz.aitu.entities.Course;
import kz.aitu.entities.StudentSchedule;
import kz.aitu.exceptions.ConflictException;
import kz.aitu.repositories.EnrollmentRepository;

import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository repo;

    public EnrollmentService(EnrollmentRepository repo) {
        this.repo = repo;
    }

    public Enrollment registerStudent(int studentId, int courseId) {
        if (repo.exists(studentId, courseId)) {
            throw new ConflictException(
                    "Student already enrolled in this course"
            );
        }
        return repo.enroll(studentId, courseId);
    }

    public List<Enrollment> getAll() {
        return repo.findAll();
    }


    public StudentSchedule buildStudentSchedule(
            Student student,
            List<Course> courses
    ) {
        return new StudentSchedule.Builder()
                .student(student)
                .courses(courses)
                .build();
    }
}
