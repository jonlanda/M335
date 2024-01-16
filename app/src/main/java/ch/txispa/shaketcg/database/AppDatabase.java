package ch.txispa.shaketcg.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import ch.txispa.shaketcg.database.dao.CharacterDao;
import ch.txispa.shaketcg.database.dao.UserCharacterCrossRefDao;
import ch.txispa.shaketcg.database.dao.UserDao;
import ch.txispa.shaketcg.database.data.Characters;
import ch.txispa.shaketcg.database.data.Users;
import ch.txispa.shaketcg.database.entity.Character;
import ch.txispa.shaketcg.database.entity.User;
import ch.txispa.shaketcg.database.entity.UserCharacterCrossRef;

@Database(entities = {User.class, Character.class, UserCharacterCrossRef.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static final String  DATABASE_NAME = "shaketcg";

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract CharacterDao characterDao();
    public abstract UserCharacterCrossRefDao userCharacterCrossRefDao();

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {
                CharacterDao characterDao = instance.characterDao();
                UserDao userDao = instance.userDao();

                Characters characters = new Characters(characterDao);
                characters.defaultData();
                Users users = new Users(userDao);
                users.defaultData();
            });
        }
    };

    public static AppDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, DATABASE_NAME)
                            .addCallback(roomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
}
