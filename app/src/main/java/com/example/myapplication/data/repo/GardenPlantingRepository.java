package com.example.myapplication.data.repo;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.dao.GardenPlantingDao;
import com.example.myapplication.data.entity.GardenPlanting;
import com.example.myapplication.data.vo.PlantAndGardenPlantings;

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

    Long createGardenPlanting(String plantId) {
        GardenPlanting gardenPlanting = new GardenPlanting(plantId);
        return gardenPlantingDao.insertGardenPlanting(gardenPlanting);
    }

    void removeGardenPlanting(GardenPlanting gardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting);
    }

    LiveData<Boolean> isPlanted(String plantId) {
       return gardenPlantingDao.isPlanted(plantId);
    }

    LiveData<List<PlantAndGardenPlantings>> getPlantedGardens() {
        return gardenPlantingDao.getPlantedGardens();
    }
}
