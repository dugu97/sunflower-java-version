package com.example.myapplication.data.vo;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.myapplication.data.entity.GardenPlanting;
import com.example.myapplication.data.entity.Plant;

import java.util.Collections;
import java.util.List;

public class PlantAndGardenPlantings {

    @Embedded
    private Plant plant;

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    private List<GardenPlanting> gardenPlantings = Collections.emptyList();

    public Plant getPlant() {
        return plant;
    }

    public List<GardenPlanting> getGardenPlantings() {
        return gardenPlantings;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public void setGardenPlantings(List<GardenPlanting> gardenPlantings) {
        this.gardenPlantings = gardenPlantings;
    }
}
