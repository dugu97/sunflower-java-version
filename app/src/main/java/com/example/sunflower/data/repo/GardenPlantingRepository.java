package com.example.sunflower.data.repo;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.sunflower.data.dao.GardenPlantingDao;
import com.example.sunflower.data.entity.GardenPlanting;
import com.example.sunflower.data.vo.PlantAndGardenPlantings;

import java.util.List;

public class GardenPlantingRepository {

    private static volatile GardenPlantingRepository instance;
    private GardenPlantingDao gardenPlantingDao;

    private GardenPlantingRepository(GardenPlantingDao gardenPlantingDao) {
        this.gardenPlantingDao = gardenPlantingDao;
    }

    public static GardenPlantingRepository getInstance(GardenPlantingDao gardenPlantingDao){
        if (instance == null) {
            synchronized(GardenPlantingRepository.class){
                if(instance == null){
                    instance = new GardenPlantingRepository(gardenPlantingDao);
                }
            }
        }
        return instance;
    }

    public void createGardenPlanting(final String plantId) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                GardenPlanting gardenPlanting = new GardenPlanting(plantId);
                gardenPlantingDao.insertGardenPlanting(gardenPlanting);
            }
        });
    }

    void removeGardenPlanting(GardenPlanting gardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting);
    }

    public LiveData<Boolean> isPlanted(String plantId) {
       return gardenPlantingDao.isPlanted(plantId);
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantedGardens() {
        return gardenPlantingDao.getPlantedGardens();
    }
}
