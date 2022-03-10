package ru.job4j.autosale.store;

import org.junit.Test;
import ru.job4j.autosale.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class HbmStoreUserTest {

    @Test
    public void whenSaveUser() {
        StoreUser storeUser = HbmStoreUser.instOf();
        User user = User.of("Vasya", "987654");
        storeUser.addUser(user);
        User userFromBD =storeUser.findUser("Vasya");
        assertThat(userFromBD.getPassword(), is(user.getPassword()));
    }

}