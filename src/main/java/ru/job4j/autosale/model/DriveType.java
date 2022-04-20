package ru.job4j.autosale.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "driver_type")
public class DriveType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static DriveType of(String name) {
        DriveType driverType = new DriveType();
        driverType.name = name;
        return driverType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DriveType driveType = (DriveType) o;
        return id == driveType.id && Objects.equals(name, driveType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "DriveType{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
