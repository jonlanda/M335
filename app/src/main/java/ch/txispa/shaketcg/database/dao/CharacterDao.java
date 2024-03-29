package ch.txispa.shaketcg.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import ch.txispa.shaketcg.database.entity.Character;

@Dao
public interface CharacterDao {
    @Transaction
    @Query("SELECT * FROM character")
    List<Character> getAll();

    @Transaction
    @Query("SELECT * FROM character WHERE name LIKE :name LIMIT 1")
    Character findByName(String name);

    @Transaction
    @Query("SELECT * FROM character WHERE id LIKE :characterId LIMIT 1")
    Character getCharacterById(int characterId);

    @Transaction
    @Query("SELECT * FROM character GROUP BY series")
    List<Character> getAllSortedBySeries();

    @Transaction
    @Query("SELECT * FROM character WHERE series LIKE :series")
    List<Character> getCharacterFromSeries(String series);

    @Transaction
    @Query("SELECT * FROM character WHERE rarity LIKE :rarity")
    List<Character> getAllOfRarity(String rarity);

    @Transaction
    @Insert
    void insertAll(Character... characters);

    @Transaction
    @Delete
    void delete(Character character);
}
