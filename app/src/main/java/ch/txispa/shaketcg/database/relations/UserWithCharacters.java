package ch.txispa.shaketcg.database.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

public class UserWithCharacters {
    @Embedded
    public User user;
    @Relation(
            parentColumn = "userId",
            entityColumn = "characterId",
            associateBy = @Junction(UserCharacterCrossRef.class)
    )
    public List<Character> ownedCharacters;
}

