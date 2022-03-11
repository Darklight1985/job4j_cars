package ru.job4j.autosale.store;

import ru.job4j.autosale.model.*;

import java.util.List;

public interface StoreCar {

    void addCar(Car car);

    Car findCar(int id);

    List<Model> findModel();

    List<BodyType> findBody();

    List<EngineType> findEngine();

    List<DriveType> findDrive();

    Car createCar(String power, String modelId, String bodyId,
                  String engineId, String driveID);
}
