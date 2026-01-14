package kz.aitu;

import kz.aitu.db.PostgresDB;
import kz.aitu.repositories.StudentRepositoryImpl;
import kz.aitu.services.StudentService;

public class   Main {
    public static void main(String[] args) {
        StudentService service = new StudentService(
                new StudentRepositoryImpl(new PostgresDB())
        );
        service.addStudent("Zhannel", 15);
        System.out.println(service.getAll());

    }

}
