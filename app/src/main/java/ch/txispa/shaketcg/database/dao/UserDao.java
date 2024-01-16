package ch.txispa.shaketcg.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;

@Dao
public interface UserDao {
    @Transaction
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Transaction
    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    User findByUsername(String username);

    @Transaction
    @Query("SELECT * FROM character " +
            "INNER JOIN UserCharacterCrossRef ON character.id = UserCharacterCrossRef.characterId " +
            "WHERE UserCharacterCrossRef.userId = :userId")
    List<Character> getCharactersByUserId(int userId);

    @Transaction
    @Query("UPDATE user SET money = :newMoneyValue WHERE id = :userId")
    void updateMoney(int userId, int newMoneyValue);

    @Transaction
    @Insert
    void insertAll(User... users);

    @Transaction
    @Delete
    void delete(User user);
}
