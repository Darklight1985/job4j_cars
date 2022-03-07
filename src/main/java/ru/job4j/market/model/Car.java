package ru.job4j.market.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "markAvto_id", foreignKey = @ForeignKey(name = "MARK_ID_FK"))
    private MarkAvto markAvto;

    @ManyToOne
    @JoinColumn(name = "bodyType_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private BodyType bodyType;

    public static Car of(MarkAvto markAvto, BodyType bodyType) {
       Car car = new Car();
       car.markAvto = markAvto;
       car.bodyType = bodyType;
       return  car;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MarkAvto getMarkAvto() {
        return markAvto;
    }

    public void setMarkAvto(MarkAvto markAvto) {
        this.markAvto = markAvto;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return id == car.id && Objects.equals(markAvto, car.markAvto) && Objects.equals(bodyType, car.bodyType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, markAvto, bodyType);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", markAvto=" + markAvto
                + ", bodyType=" + bodyType
                + '}';
    }
}
