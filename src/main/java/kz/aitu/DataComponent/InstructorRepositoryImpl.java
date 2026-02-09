package kz.aitu.DataComponent;

import kz.aitu.repositories.InstructorRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InstructorRepositoryImpl implements InstructorRepository {

    private final IDB db;

    public InstructorRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Integer findIdByName(String name) {
        String sql = "SELECT id FROM instructors WHERE name = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
