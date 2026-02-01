package kz.aitu.entities;

import java.util.List;

public class StudentSchedule {

    private final Student student;
    private final List<Course> courses;

    private StudentSchedule(Builder builder) {
        this.student = builder.student;
        this.courses = builder.courses;
    }

    public Student getStudent() {
        return student;
    }

    public List<Course> getCourses() {
        return courses;
    }


    public static class Builder {

        private Student student;
        private List<Course> courses;

        public Builder student(Student student) {
            this.student = student;
            return this;
        }

        public Builder courses(List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public StudentSchedule build() {
            return new StudentSchedule(this);
        }
    }
}

