package ru.job4j.autosale.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String power;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id", foreignKey = @ForeignKey(name = "MODEL_ID_FK"))
    private Model model;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "bodyType_id", foreignKey = @ForeignKey(name = "BODY_ID_FK"))
    private BodyType bodyType;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private EngineType engine;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "driverType_id", foreignKey = @ForeignKey(name = "DRIVERT_ID_FK"))
    private DriveType driveType;

    public static Car of(String power, Model model, BodyType bodyType,
               EngineType engine, DriveType driveType) {
        Car car = new Car();
        car.power = power;
        car.model = model;
        car.bodyType = bodyType;
        car.engine = engine;
        car.driveType = driveType;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public DriveType getDriveType() {
        return driveType;
    }

    public void setDriveType(DriveType driveType) {
        this.driveType = driveType;
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", power='" + power + '\''
                + ", modelAvto=" + model
                + ", bodyType=" + bodyType
                + ", engine=" + engine
                + ", driveType=" + driveType
                + '}';
    }
}
