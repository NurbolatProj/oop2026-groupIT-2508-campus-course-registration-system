package kz.aitu.entities;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;

    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "Enrollment{id=%d, studentId=%d, courseId=%d}"
                .formatted(id, studentId, courseId);
    }
}
