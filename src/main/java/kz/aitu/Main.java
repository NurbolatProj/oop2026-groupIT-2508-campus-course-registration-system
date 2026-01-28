package kz.aitu;

import kz.aitu.db.IDB;
import kz.aitu.db.PostgresDB;
import kz.aitu.repositories.*;
import kz.aitu.services.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        IDB db = new PostgresDB();

        StudentRepository studentRepo = new StudentRepositoryImpl(db);
        CourseRepository courseRepo = new CourseRepositoryImpl(db);
        EnrollmentRepository enrollmentRepo = new EnrollmentRepositoryImpl(db);

        StudentService studentService = new StudentService(studentRepo);
        CourseService courseService = new CourseService(courseRepo);
        EnrollmentService enrollmentService = new EnrollmentService(enrollmentRepo);

        Scanner scanner = new Scanner(System.in);

        System.out.println("✅ Connected successfully!");

        while (true) {
            System.out.println("""
                    
                    ===== MENU =====
                    1. Add student
                    2. Add course
                    3. Enroll student to course
                    4. Show all students
                    5. Show all courses
                    6. Show all enrollments
                    0. Exit
                    =================
                    """);

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();

            try {
                switch (choice) {

                    case 1 -> {
                        scanner.nextLine();
                        System.out.print("Student name: ");
                        String name = scanner.nextLine();

                        System.out.print("Age: ");
                        int age = scanner.nextInt();

                        studentService.addStudent(name, age);
                        System.out.println("✅ Student added");
                    }


                    case 2 -> {
                        scanner.nextLine();

                        System.out.print("Course title: ");
                        String title = scanner.nextLine();

                        System.out.print("Credits: ");
                        int credits = scanner.nextInt();

                        System.out.print("Instructor ID (or 0 if none): ");
                        Integer instructorId = scanner.nextInt();
                        if (instructorId == 0) instructorId = null;

                        System.out.print("Department ID (or 0 if none): ");
                        Integer departmentId = scanner.nextInt();
                        if (departmentId == 0) departmentId = null;

                        System.out.print("Trimester ID (or 0 if none): ");
                        Integer trimesterId = scanner.nextInt();
                        if (trimesterId == 0) trimesterId = null;

                        System.out.print("Classroom ID (or 0 if none): ");
                        Integer classroomId = scanner.nextInt();
                        if (classroomId == 0) classroomId = null;

                        courseService.addCourse(
                                title,
                                credits,
                                instructorId,
                                departmentId,
                                trimesterId,
                                classroomId
                        );

                        System.out.println("✅ Course added");
                    }


                    case 3 -> {
                        System.out.print("Student ID: ");
                        int studentId = scanner.nextInt();

                        System.out.print("Course ID: ");
                        int courseId = scanner.nextInt();

                        enrollmentService.registerStudent(studentId, courseId);
                        System.out.println("✅ Student enrolled");
                    }

                    case 4 -> {
                        System.out.println(studentService.getAll());
                    }

                    case 5 -> {
                        System.out.println(courseService.getAllCourses());
                    }

                    case 6 -> {
                        System.out.println(enrollmentService.getAll());
                    }

                    case 0 -> {
                        System.out.println("👋 Bye");
                        return;
                    }

                    default -> System.out.println("❌ Invalid option");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
