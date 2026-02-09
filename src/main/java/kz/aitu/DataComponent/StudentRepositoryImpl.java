package kz.aitu.DataComponent;

import kz.aitu.CoreComponent.entities.Student;
import kz.aitu.exceptions.NotFoundException;
import kz.aitu.repositories.StudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final IDB db;

    public StudentRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public boolean create(Student student) {
        String sql = "INSERT INTO public.students(name, age) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.executeUpdate();

            return true; //

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM public.students WHERE id = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new NotFoundException("Student not found");
            }

            Student s = new Student(
                    rs.getString("name"),
                    rs.getInt("age")
            );
            s.setId(rs.getInt("id"));
            return s;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM public.students";

        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getString("name"),
                        rs.getInt("age")
                );
                s.setId(rs.getInt("id"));
                list.add(s);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    @Override
    public Integer findIdByName(String name) {
        String sql = "SELECT id FROM public.students WHERE name = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
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
