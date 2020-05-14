package com.example.myapplication.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;

import com.example.myapplication.data.dao.GardenPlantingDao;
import com.example.myapplication.data.dao.PlantDao;
import com.example.myapplication.data.entity.GardenPlanting;
import com.example.myapplication.data.entity.Plant;
import com.example.myapplication.workers.SeedDatabaseWorker;

@Database(entities = {GardenPlanting.class, Plant.class}, version = 1, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract GardenPlantingDao gardenPlantingDao();
    public abstract PlantDao plantDao();

    private static volatile AppDatabase instance;

    public AppDatabase() {
    }

    public static AppDatabase getInstance(Context context){
        if (instance == null) {
            synchronized(AppDatabase.class){
                if(instance == null){
                    instance = buildDatabase(context);
                }
            }
        }
        return instance;
    }

    // Create and pre-populate the database. See this article for more details:
    // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "sunflower-db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        WorkRequest request = new OneTimeWorkRequest.Builder(SeedDatabaseWorker.class).build();
                        WorkManager.getInstance(context).enqueue(request);
                    }
                }).build();
    }
}
