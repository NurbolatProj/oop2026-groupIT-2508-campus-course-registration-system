package kz.aitu.repositories;

import kz.aitu.entities.Enrollment;
import java.util.List;

public interface EnrollmentRepository {
    Enrollment enroll(int studentId, int courseId);
    boolean exists(int studentId, int courseId);
    List<Enrollment> findAll();
}
