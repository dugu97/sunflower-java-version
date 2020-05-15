package com.example.sunflower.data.repo;

import androidx.lifecycle.LiveData;

import com.example.sunflower.data.dao.PlantDao;
import com.example.sunflower.data.entity.Plant;

import java.util.List;

public class PlantRepository {

    private static volatile PlantRepository instance;
    private PlantDao plantDao;

    private PlantRepository(PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public static PlantRepository getInstance(PlantDao plantDao){
        if (instance == null) {
            synchronized(PlantRepository.class){
                if(instance == null){
                    instance = new PlantRepository(plantDao);
                }
            }
        }
        return instance;
    }

    public LiveData<List<Plant>> getPlants(){
       return plantDao.getPlants();
    }

    public LiveData<Plant> getPlant(String plantId){
        return plantDao.getPlant(plantId);
    }

    public LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber){
        return plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }
}
