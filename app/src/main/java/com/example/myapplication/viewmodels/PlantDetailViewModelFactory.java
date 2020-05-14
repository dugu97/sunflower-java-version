package com.example.myapplication.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.data.repo.GardenPlantingRepository;
import com.example.myapplication.data.repo.PlantRepository;

public class PlantDetailViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private PlantRepository plantRepository;
    private GardenPlantingRepository gardenPlantingRepository;
    private String plantId;

    public PlantDetailViewModelFactory(PlantRepository plantRepository, GardenPlantingRepository gardenPlantingRepository, String plantId) {
        this.plantRepository = plantRepository;
        this.gardenPlantingRepository = gardenPlantingRepository;
        this.plantId = plantId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId);
    }
}
