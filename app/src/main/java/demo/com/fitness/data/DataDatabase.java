package demo.com.fitness.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Event.class,Teacher.class},version = 1,exportSchema = false)
public abstract class DataDatabase extends RoomDatabase {

    private static final String DB_NAME = "fitness.db";
    private static DataDatabase database;
    private static final Object LOCK = new Object();


    public static DataDatabase getInstance(Context context){

        synchronized (LOCK){

            if (database == null){
              database = Room.databaseBuilder(context,DataDatabase.class,DB_NAME).fallbackToDestructiveMigration().build();

            }
        }


        return  database;
    }


    public abstract DataDao dataDao();
}
