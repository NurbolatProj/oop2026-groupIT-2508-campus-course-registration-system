package kz.aitu.entities;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;

    public Enrollment(int studentId, int courseId) {
        this.studentId = studentId;

        this.courseId = courseId;
    }

    public int getStudentId() { return studentId; }
    public int getCourseId() { return courseId; }
}
