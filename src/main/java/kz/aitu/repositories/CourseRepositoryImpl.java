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
    public void create(Course course) {
        String sql = "INSERT INTO public.courses(title, credits) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, course.getTitle());
            ps.setInt(2, course.getCredits());
            ps.execute();

        } catch (Exception e) {
            throw new RuntimeException("Course create failed");
        }
    }

    @Override
    public List<Course> findAll() {
        List<Course> list = new ArrayList<>();
        String sql = "SELECT * FROM public.courses";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Course c = new Course(
                        rs.getString("title"),
                        rs.getInt("credits")
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
