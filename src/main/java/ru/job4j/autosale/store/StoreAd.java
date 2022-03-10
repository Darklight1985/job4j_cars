package ru.job4j.autosale.store;

import ru.job4j.autosale.model.Ad;

import java.util.List;

public interface StoreAd {

    void addAd(Ad ad);

    List<Ad> findAll();

    public List<Ad> findNotFinal();
}
