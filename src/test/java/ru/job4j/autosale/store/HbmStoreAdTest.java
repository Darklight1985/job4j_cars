package ru.job4j.autosale.store;

import org.junit.Test;
import ru.job4j.autosale.model.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmStoreAdTest {

    @Test
    public void whenSaveAd() {
        StoreCar storeCar = HbmStoreCar.instOf();
        StoreAd storeAd = HbmStoreAd.instOf();
        Model kiaRio = Model.of("KiaRio");
        EngineType gasoline = EngineType.of("Бензин");
        BodyType sedan = BodyType.of("Седан");
        DriveType front = DriveType.of("Передний");
        Car car = Car.of("125", kiaRio, sedan, gasoline, front);
        storeCar.addCar(car);
        User user = User.of("Misha", "qwe123");
        Ad ad = Ad.of("something", car, user,false);
        storeAd.addAd(ad);
        List<Ad> list = storeAd.findAll();
        assertThat(list.get(0).getDescription(), is(ad.getDescription()));
    }

    @Test
    public void whenFindNotFinal() {
        StoreCar storeCar = HbmStoreCar.instOf();
        StoreAd storeAd = HbmStoreAd.instOf();
        Model kiaRio = Model.of("KiaRio");
        EngineType gasoline = EngineType.of("Бензин");
        BodyType sedan = BodyType.of("Седан");
        DriveType front = DriveType.of("Передний");
        Car car = Car.of("125", kiaRio, sedan, gasoline, front);
        storeCar.addCar(car);
        User user = User.of("Misha", "qwe123");
        Ad adOne = Ad.of("something", car, user, false);
        Ad adTwo = Ad.of("something", car, user, true);
        Ad adThree = Ad.of("something", car, user,false);
        storeAd.addAd(adOne);
        storeAd.addAd(adTwo);
        storeAd.addAd(adThree);
        List<Ad> list = storeAd.findNotFinal();
        assertThat(list.size(), is(2));
    }

}