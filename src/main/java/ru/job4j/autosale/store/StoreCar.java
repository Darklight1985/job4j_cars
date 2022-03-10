package ru.job4j.autosale.store;

import ru.job4j.autosale.model.Car;
import ru.job4j.autosale.model.EngineType;

import java.util.List;

public interface StoreCar {

    void addCar(Car car);

    Car findCar(int id);

}
