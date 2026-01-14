package kz.aitu.repositories;

import kz.aitu.db.IDB;
import kz.aitu.entities.Student;
import kz.aitu.exceptions.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {

    private final IDB db;

    public StudentRepositoryImpl(IDB db) {
        this.db = db;
    }


    @Override
    public void create(Student student) {
        String sql = "INSERT INTO public.students(name, age) VALUES (?, ?)";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Create failed: " + e.getMessage());
        }
    }


    @Override
    public Student findById(int id) {
        String sql = "SELECT * FROM public.students WHERE id=?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next())
                throw new NotFoundException("Student not found");

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
