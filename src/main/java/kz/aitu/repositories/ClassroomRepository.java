package kz.aitu.repositories;

public interface ClassroomRepository {
    Integer findIdByRoom(String room);
}
// данные берет с базы данных и изменяет.