package kz.aitu.repositories;

import kz.aitu.db.IDB;
import kz.aitu.entities.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {

    private final IDB db;

    public EnrollmentRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Enrollment enroll(int studentId, int courseId) {
        String sql = """
                INSERT INTO enrollments (student_id, course_id)
                VALUES (?, ?)
                RETURNING id
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("id"),
                        studentId,
                        courseId
                );
            }

            throw new RuntimeException("Enrollment failed");

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean exists(int studentId, int courseId) {
        String sql = """
                SELECT 1 FROM enrollments
                WHERE student_id = ? AND course_id = ?
                """;

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Enrollment> findAll() {
        List<Enrollment> list = new ArrayList<>();

        String sql = "SELECT id, student_id, course_id FROM enrollments";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Enrollment(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return list;
    }
}
