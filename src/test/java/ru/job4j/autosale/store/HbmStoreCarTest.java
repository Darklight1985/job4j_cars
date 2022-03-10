package ru.job4j.autosale.store;

import org.junit.Test;
import ru.job4j.autosale.model.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmStoreCarTest {

    @Test
    public void whenSaveCar() {
        StoreCar storeCar = HbmStoreCar.instOf();
        Model kiaRio = Model.of("KiaRio");
        EngineType gasoline = EngineType.of("Бензин");
        BodyType sedan = BodyType.of("Седан");
        DriveType front = DriveType.of("Передний");
        Car car = Car.of("125", kiaRio, sedan, gasoline, front);
        storeCar.addCar(car);
        Car result = storeCar.findCar(car.getId());
        assertThat(result.getMarkAvto(), is(car.getMarkAvto()));
    }

}