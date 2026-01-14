package kz.aitu.entities;

public class Course {
    private int id;
    private String title;
    private int credits;

    public Course() {}



    public Course(String title, int credits) {
        this.title = title;
        this.credits = credits;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getCredits() { return credits; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return "Course{id=" + id +
                ", title='" + title + '\'' +
                ", credits=" + credits + '}';
    }
}
