package ch.txispa.shaketcg.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

@Dao
public interface UserCharacterCrossRefDao {
    @Transaction
    @Insert
    void insert(UserCharacterCrossRef userCharacterCrossRef);

    @Transaction
    @Delete
    void delete(UserCharacterCrossRef userCharacterCrossRef);

    @Transaction
    @Query("SELECT * FROM usercharactercrossref WHERE userId = :userId AND characterId = :characterId")
    UserCharacterCrossRef getUserCharacterCrossRef(int userId, int characterId);
}

