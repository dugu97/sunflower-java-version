package com.example.sunflower.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sunflower.data.repo.GardenPlantingRepository;
import com.example.sunflower.data.vo.PlantAndGardenPlantings;

import java.util.List;

public class GardenPlantingListViewModel extends ViewModel {

    private GardenPlantingRepository gardenPlantingRepository;

    public GardenPlantingListViewModel(GardenPlantingRepository gardenPlantingRepository) {
        this.gardenPlantingRepository = gardenPlantingRepository;
        plantAndGardenPlantings = this.gardenPlantingRepository.getPlantedGardens();
    }

    public LiveData<List<PlantAndGardenPlantings>> getPlantAndGardenPlantings() {
        return plantAndGardenPlantings;
    }

    public void setPlantAndGardenPlantings(LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings) {
        this.plantAndGardenPlantings = plantAndGardenPlantings;
    }

    private LiveData<List<PlantAndGardenPlantings>> plantAndGardenPlantings;
}
