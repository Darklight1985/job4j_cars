package ru.job4j.autosale.store;

import ru.job4j.autosale.model.User;

public interface StoreUser {

    void addUser(User user);

    User findUser(String nameUser);
}
