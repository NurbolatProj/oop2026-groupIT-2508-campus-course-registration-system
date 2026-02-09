package kz.aitu.CoreComponent.entities;

public class Course {
    private int id;
    private String title;
    private int credits;
    private Integer instructorId;
    private Integer classroomId;
    private String instructorName;

    public Course(String title, int credits,
                  Integer instructorId,
                  Integer classroomId) {
        this.title = title;
        this.credits = credits;
        this.instructorId = instructorId;
        this.classroomId = classroomId;
    }

    // getters
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Integer getInstructorId() { return instructorId; }
    public Integer getClassroomId() { return classroomId; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", instructor='" + instructorName + '\'' +
                '}';
    }


    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }


}
