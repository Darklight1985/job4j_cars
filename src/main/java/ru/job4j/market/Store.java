package ru.job4j.market;


import ru.job4j.market.model.Item;
import ru.job4j.market.model.MarkAvto;

import java.util.List;

public interface Store {

    List<Item> findAllItemDay();

    List<Item> findItemWithMark(MarkAvto markAvto);

    public List<Item> findItemWithPhoto();
}