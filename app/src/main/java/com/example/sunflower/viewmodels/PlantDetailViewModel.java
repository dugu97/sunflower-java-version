package com.example.sunflower.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sunflower.data.entity.Plant;
import com.example.sunflower.data.repo.GardenPlantingRepository;
import com.example.sunflower.data.repo.PlantRepository;

public class PlantDetailViewModel extends ViewModel {
    PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;
    private LiveData<Boolean> isPlanted;

    public LiveData<Boolean> getIsPlanted() {
        return isPlanted;
    }

    public void setIsPlanted(LiveData<Boolean> isPlanted) {
        this.isPlanted = isPlanted;
    }

    public LiveData<Plant> getPlant() {
        return plant;
    }

    public void setPlant(LiveData<Plant> plant) {
        this.plant = plant;
    }

    private LiveData<Plant> plant;

    public PlantDetailViewModel(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
        isPlanted = gardenPlantingRepository.isPlanted(plantId);
        plant = plantRepository.getPlant(plantId);
    }


    public void addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId);
    }
}
