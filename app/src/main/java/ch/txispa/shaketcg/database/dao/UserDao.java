package ch.txispa.shaketcg.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.ArrayList;
import java.util.List;

import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username LIKE :username LIMIT 1")
    User findByUsername(String username);

    @Query("SELECT * FROM character " +
            "INNER JOIN UserCharacterCrossRef ON character.id = UserCharacterCrossRef.characterId " +
            "WHERE UserCharacterCrossRef.userId = :userId")
    List<Character> getCharactersByUserId(int userId);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}
