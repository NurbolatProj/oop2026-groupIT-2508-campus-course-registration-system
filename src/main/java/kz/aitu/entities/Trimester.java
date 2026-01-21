package kz.aitu.entities;

public class Trimester {
    private int id;
    private String name;
    private int year;

    public Trimester(int id, String name, int year) {
        this.id = id;
        this.name = name;
        this.year = year;
    }

    public Trimester(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "Trimester{id=%d, name='%s', year=%d}"
                .formatted(id, name, year);
    }
}
