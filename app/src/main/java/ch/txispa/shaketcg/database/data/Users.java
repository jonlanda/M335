package ch.txispa.shaketcg.database.data;

import ch.txispa.shaketcg.database.dao.CharacterDao;
import ch.txispa.shaketcg.database.dao.UserDao;
import ch.txispa.shaketcg.database.entity.User;

public class Users {
    private UserDao userDao;
    public Users(UserDao userDao) {
        this.userDao = userDao;
    }

    public void defaultData() {
        User defaultUser = new User();
        defaultUser.username = "user";
        defaultUser.money = 100;
        defaultUser.id = 1;
        userDao.insertAll(defaultUser);
    }
}
