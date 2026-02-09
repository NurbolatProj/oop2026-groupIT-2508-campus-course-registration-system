package kz.aitu.CoreComponent.entities;

public class Enrollment {

    private int id;
    private String studentName;
    private String courseTitle;

    public Enrollment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student='" + studentName + '\'' +
                ", course='" + courseTitle + '\'' +
                '}';
    }
}
