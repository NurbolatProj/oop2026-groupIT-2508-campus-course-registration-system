package kz.aitu.entities;

public class Instructor {
    private int id;
    private String name;

    public Instructor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Instructor(String name) {
        this.name = name;
    }

    public int getId() {  return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Instructor{id=%d, name='%s'}"
                .formatted(id, name);
    }
}
