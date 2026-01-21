package kz.aitu.entities;

public class Course {
    private int id;
    private String title;
    private int credits;
    private Integer instructorId;
    private Integer departmentId;
    private Integer trimesterId;
    private Integer classroomId;

    public Course(String title, int credits,
                  Integer instructorId,
                  Integer departmentId,
                  Integer trimesterId,
                  Integer classroomId) {
        this.title = title;
        this.credits = credits;
        this.instructorId = instructorId;
        this.departmentId = departmentId;
        this.trimesterId = trimesterId;
        this.classroomId = classroomId;
    }

    // getters
    public String getTitle() { return title; }
    public int getCredits() { return credits; }
    public Integer getInstructorId() { return instructorId; }
    public Integer getDepartmentId() { return departmentId; }
    public Integer getTrimesterId() { return trimesterId; }
    public Integer getClassroomId() { return classroomId; }

    public void setId(int id) { this.id = id; }
}
