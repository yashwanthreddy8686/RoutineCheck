package com.yashwanth.routinecheck.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.yashwanth.routinecheck.DB.DAO.TaskDao;
import com.yashwanth.routinecheck.DB.Entity.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile AppDatabase instance;

    public static AppDatabase getDatabaseInstance(final Context context){
        if (instance == null){
            synchronized (AppDatabase.class){
                if (instance == null){
                    instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "app_database") //Name of Database in SQL Database
                            .fallbackToDestructiveMigration() // Migration is database modified/changed
                            .build();
                }
            }
        }
        return instance;
    }
}
