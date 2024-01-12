package ch.txispa.shaketcg.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;

@Dao
public interface CharacterDao {
    @Query("SELECT * FROM character")
    List<Character> getAll();

    @Query("SELECT * FROM character WHERE name LIKE :name LIMIT 1")
    Character findByName(String name);

    @Query("SELECT * FROM character WHERE id LIKE :characterId LIMIT 1")
    Character getCharacterById(int characterId);

    @Query("SELECT * FROM character GROUP BY series")
    List<Character> getAllSortedBySeries();

    @Query("SELECT * FROM character WHERE series LIKE :series")
    List<Character> getCharacterFromSeries(String series);

    @Insert
    void insertAll(Character... characters);

    @Delete
    void delete(Character character);
}
