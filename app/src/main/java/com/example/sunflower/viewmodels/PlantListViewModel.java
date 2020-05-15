package com.example.sunflower.viewmodels;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.sunflower.data.entity.Plant;
import com.example.sunflower.data.repo.PlantRepository;

import java.util.List;

public class PlantListViewModel extends ViewModel {
    private static final int NO_GROW_ZONE = -1;
    private static final String GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY";

    private PlantRepository plantRepository;
    private SavedStateHandle savedStateHandle;

    public PlantListViewModel(final PlantRepository plantRepository, SavedStateHandle savedStateHandle) {
        this.plantRepository = plantRepository;
        this.savedStateHandle = savedStateHandle;
        this.plants = Transformations.switchMap(getSavedGrowZoneNumber(), new Function<Integer, LiveData<List<Plant>>>() {
             @Override
             public LiveData<List<Plant>> apply(Integer input) {
                 if (input == NO_GROW_ZONE) {
                     return plantRepository.getPlants();
                 } else {
                     return plantRepository.getPlantsWithGrowZoneNumber(input);
                 }
             }
         });
    }

    public LiveData<List<Plant>> getPlants() {
        return plants;
    }

    LiveData<List<Plant>> plants;


    public void setGrowZoneNumber(int num) {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, num);
    }

    public void clearGrowZoneNumber() {
        savedStateHandle.set(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE);
    }

    public boolean isFiltered() {
        return getSavedGrowZoneNumber().getValue() != NO_GROW_ZONE;
    }

    private MutableLiveData<Integer> getSavedGrowZoneNumber() {
        return savedStateHandle.getLiveData(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE);
    }
}
