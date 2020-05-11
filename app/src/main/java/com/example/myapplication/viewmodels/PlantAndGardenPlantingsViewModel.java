package com.example.myapplication.viewmodels;

import com.example.myapplication.data.entity.GardenPlanting;
import com.example.myapplication.data.entity.Plant;
import com.example.myapplication.data.vo.PlantAndGardenPlantings;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static androidx.core.util.Preconditions.checkNotNull;

public class PlantAndGardenPlantingsViewModel {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.US);

    private PlantAndGardenPlantings plantings;
    private Plant plant;
    private GardenPlanting gardenPlanting;

    String waterDateString = dateFormat.format(gardenPlanting.getLastWateringDate().getTime());
    int wateringInterval;
    String imageUrl;
    String plantName;
    String plantDateString = dateFormat.format(gardenPlanting.getPlantDate().getTime());
    String plantId;

    public PlantAndGardenPlantingsViewModel(PlantAndGardenPlantings plantings) {
        this.plantings = plantings;
        this.plant = plantings.getPlant();
        this.gardenPlanting = plantings.getGardenPlantings().get(0);
        this.wateringInterval = plant.getWateringInterval();
        this.imageUrl = plant.getImageUrl();
        this.plantName = plant.getName();
        this.plantId = plant.getPlantId();
    }

    public void setWaterDateString(String waterDateString) {
        this.waterDateString = waterDateString;
    }

    public void setWateringInterval(int wateringInterval) {
        this.wateringInterval = wateringInterval;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public void setPlantDateString(String plantDateString) {
        this.plantDateString = plantDateString;
    }

    public void setPlantId(String plantId) {
        this.plantId = plantId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWaterDateString() {
        return waterDateString;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getPlantDateString() {
        return plantDateString;
    }

    public String getPlantId() {
        return plantId;
    }
}
