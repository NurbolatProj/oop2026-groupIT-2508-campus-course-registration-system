package kz.aitu.BuilderComponent;

import kz.aitu.CoreComponent.entities.Course;

public class CourseFactory {

    public static Course createCourse(
            String type,
            String title,
            int credits,
            int instructorId,
            int classroomId
    ) {
        return switch (type.toUpperCase()) {
            case "LAB" -> new Course(title, credits, instructorId, classroomId);
            case "ONLINE" -> new Course(title, credits, instructorId, classroomId);
            default -> new Course(title, credits, instructorId, classroomId);
        };
    }
}

