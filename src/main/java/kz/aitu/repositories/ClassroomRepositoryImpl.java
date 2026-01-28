package kz.aitu.repositories;

import kz.aitu.db.IDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClassroomRepositoryImpl implements ClassroomRepository {

    private final IDB db;

    public ClassroomRepositoryImpl(IDB db) {
        this.db = db;
    }

    @Override
    public Integer findIdByRoom(String room) {
        String sql = "SELECT id FROM classrooms WHERE room = ?";

        try (Connection con = db.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, room);
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
