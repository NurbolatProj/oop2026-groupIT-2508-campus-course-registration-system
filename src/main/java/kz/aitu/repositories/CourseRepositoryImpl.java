package kz.aitu.repositories;

import kz.aitu.db.IDB;
import kz.aitu.entities.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class    CourseRepositoryImpl implements CourseRepository {

    private final IDB db;

    public CourseRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public void create(Course course) {
        String sql = """
        
                INSERT INTO courses
        (title, credits, instructor_id, department_id, trimester_id, classroom_id)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.setObject(3, course.getInstructorId());
            ps.setObject(4, course.getDepartmentId());
            ps.setObject(5, course.getTrimesterId());
            ps.setObject(6, course.getClassroomId());

            ps.execute();

        } catch (Exception e) {
            throw new RuntimeException("Create course failed: " + e.getMessage());
        }
    }


    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();

        String sql =
                """
        SELECT id, title,
                credits,
               instructor_id,
               department_id,
               trimester_id,
               classroom_id
                    FROM courses
        """;

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("title"),
                        rs.getInt("credits"),
                        (Integer) rs.getObject("instructor_id"),
                        (Integer) rs.getObject("department_id"),
                        (Integer) rs.getObject("trimester_id"),
                        (Integer) rs.getObject("classroom_id")
                );
                c.setId(rs.getInt("id"));
                list.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
