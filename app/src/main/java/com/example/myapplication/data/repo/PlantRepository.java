package com.example.myapplication.data.repo;

import androidx.lifecycle.LiveData;

import com.example.myapplication.data.dao.PlantDao;
import com.example.myapplication.data.entity.Plant;

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

    LiveData<List<Plant>> getPlants(){
       return plantDao.getPlants();
    }

    LiveData<Plant> getPlant(String plantId){
        return plantDao.getPlant(plantId);
    }

    LiveData<List<Plant>> getPlantsWithGrowZoneNumber(int growZoneNumber){
        return plantDao.getPlantsWithGrowZoneNumber(growZoneNumber);
    }
}
