package kz.aitu.repositories;

import kz.aitu.db.IDB;
import kz.aitu.entities.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository {

    private final IDB db;

    public CourseRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(Course course) {
        String sql = """
            INSERT INTO courses
            (title, credits, instructor_id, classroom_id)
            VALUES (?, ?, ?, ?)
        """;

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.setObject(3, course.getInstructorId());
            ps.setObject(4, course.getClassroomId());

            ps.executeUpdate();
            return true;   // ✅ УСПЕХ

        } catch (SQLException e) {
            e.printStackTrace();
            return false;  // ✅ ОШИБКА
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();

        String sql = """
            SELECT
                c.id,
                c.title,
                c.credits,
                c.instructor_id,
                c.classroom_id,
                i.name AS instructor_name
            FROM courses c
            LEFT JOIN instructors i ON c.instructor_id = i.id
        """;

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("title"),
                        rs.getInt("credits"),
                        (Integer) rs.getObject("instructor_id"),
                        (Integer) rs.getObject("classroom_id")
                );
                c.setId(rs.getInt("id"));
                c.setInstructorName(rs.getString("instructor_name"));

                list.add(c);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Integer findIdByTitle(String title) {
        String sql = "SELECT id FROM courses WHERE title = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
