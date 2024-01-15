package ch.txispa.shaketcg.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

@Dao
public interface UserCharacterCrossRefDao {
    @Insert
    void insert(UserCharacterCrossRef userCharacterCrossRef);

    @Delete
    void delete(UserCharacterCrossRef userCharacterCrossRef);
}

