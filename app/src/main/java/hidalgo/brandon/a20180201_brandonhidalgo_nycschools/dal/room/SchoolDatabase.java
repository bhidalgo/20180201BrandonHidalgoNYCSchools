package hidalgo.brandon.a20180201_brandonhidalgo_nycschools.dal.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {SchoolEntity.class}, exportSchema = false, version = 1)
public abstract class SchoolDatabase extends RoomDatabase {
    private static SchoolDatabase db;

    public abstract SchoolDao schoolDao();

    public static SchoolDatabase getDatabase(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context,
                    SchoolDatabase.class, "schools")
                    .allowMainThreadQueries()
                    .build();
        }

        return db;
    }
}
