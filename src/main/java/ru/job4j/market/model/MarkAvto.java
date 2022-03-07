package ru.job4j.market.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "marks_avto")
public class MarkAvto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static MarkAvto of(String name) {
        MarkAvto markAvto = new MarkAvto();
        markAvto.name = name;
        return markAvto;
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
        MarkAvto markAvto = (MarkAvto) o;
        return id == markAvto.id && Objects.equals(name, markAvto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MarkAvto{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
