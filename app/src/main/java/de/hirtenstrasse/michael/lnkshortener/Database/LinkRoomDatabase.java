package de.hirtenstrasse.michael.lnkshortener.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import de.hirtenstrasse.michael.lnkshortener.Database.Dao.LinkDao;
import de.hirtenstrasse.michael.lnkshortener.Helpers.Converters;
import de.hirtenstrasse.michael.lnkshortener.Models.Link;

@Database(entities = {Link.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class LinkRoomDatabase extends RoomDatabase {

    private static LinkRoomDatabase INSTANCE;

    public static LinkRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LinkRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LinkRoomDatabase.class, "link_database")
                            .fallbackToDestructiveMigration()
                            .build();                    }
            }
        }
        return INSTANCE;
    }

    public abstract LinkDao linkDao();

}
