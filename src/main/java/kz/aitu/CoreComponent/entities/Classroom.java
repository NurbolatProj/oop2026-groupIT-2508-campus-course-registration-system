package kz.aitu.CoreComponent.entities;

public class Classroom {
    private int id;
    private String building;
    private String room;

    public Classroom(int id, String building, String room) {
        this.id = id;
        this.building = building;
        this.room = room;
    }

    public Classroom(String building, String room) {
        this.building = building;
        this.room = room;
    }

    public int getId() { return id; }
    public String getBuilding() { return building; }
    public String getRoom() { return room; }

    @Override
    public String toString() {
        return "Classroom{id=%d, building='%s', room='%s'}"
                .formatted(id, building, room);
    }
}
