package com.example.myapplication.workers;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.data.AppDatabase;
import com.example.myapplication.data.entity.Plant;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SeedDatabaseWorker extends Worker {

    private static final String TAG = "SeedDatabaseWorker";

    public SeedDatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        try {
            InputStream inputStream = getApplicationContext().getAssets().open("plants.json");
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
            Gson gson = new Gson();
            Type type =  new TypeToken<List<Plant>>(){}.getType();
            List<Plant> plantList = gson.fromJson(jsonReader, type);
            AppDatabase database = AppDatabase.getInstance(getApplicationContext());
            database.plantDao().insertAll(plantList);
            Result.success();
        } catch (Exception ex) {
            Log.e(TAG, "Error seeding database", ex);
            Result.failure();
        }
        return null;
    }
}
