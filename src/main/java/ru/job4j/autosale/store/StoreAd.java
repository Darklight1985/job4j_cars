package ru.job4j.autosale.store;

import ru.job4j.autosale.model.Ad;

import java.util.List;

public interface StoreAd {

    int addAd(Ad ad);

    boolean updateAd(Ad ad);

    List<Ad> findAll();

    public List<Ad> findNotFinal();

    Ad findAdById(int id);
}
