package ch.txispa.shaketcg.database.entity;

import androidx.core.content.pm.PermissionInfoCompat;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "userId"),
                @ForeignKey(entity = Character.class, parentColumns = "characterId", childColumns = "characterId")
        },
        indices = {
                @Index("userId"),
                @Index("characterId"),
                @Index(value = {"userId", "characterId"}, unique = true) // Define a unique constraint
        }
)
public class UserCharacterCrossRef {
    @PrimaryKey(autoGenerate = true)
    public int relationId;
    public int userId;
    public int characterId;
}




