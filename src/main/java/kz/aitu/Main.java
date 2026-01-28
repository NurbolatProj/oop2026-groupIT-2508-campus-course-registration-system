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
        InstructorRepository instructorRepo = new InstructorRepositoryImpl(db);
        ClassroomRepository classroomRepo = new ClassroomRepositoryImpl(db);


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
            scanner.nextLine();


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

                        scanner.nextLine();
                        System.out.print("Instructor name (or empty if none): ");
                        String instructorName = scanner.nextLine();

                        Integer instructorId = null;
                        if (!instructorName.isBlank()) {
                            instructorId = instructorRepo.findIdByName(instructorName.trim());
                            if (instructorId == null) {
                                System.out.println("❌ Instructor not found");
                                break;
                            }
                        }


                        System.out.print("Classroom room (or empty if none): ");
                        String room = scanner.nextLine();

                        Integer classroomId = null;
                        if (!room.isBlank()) {
                            classroomId = classroomRepo.findIdByRoom(room.trim());
                            if (classroomId == null) {
                                System.out.println("❌ Classroom not found");
                                break;
                            }
                        }

                        courseService.addCourse(
                                title,
                                credits,
                                instructorId,
                                classroomId
                        );

                        System.out.println("✅ Course added");
                    }


                    case 3 -> {

                        System.out.print("Student name: ");

                        String studentName = scanner.nextLine();

                        Integer studentId = studentRepo.findIdByName(studentName.trim());
                        if (studentId == null) {
                            System.out.println("❌ Student not found");
                            break;
                        }

                        System.out.print("Course title: ");
                        String courseTitle = scanner.nextLine();

                        Integer courseId = courseRepo.findIdByTitle(courseTitle.trim());
                        if (courseId == null) {
                            System.out.println("❌ Course not found");
                            break;
                        }

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
