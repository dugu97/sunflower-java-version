package com.example.myapplication.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(tableName = "plants")
public class Plant {

    @PrimaryKey @ColumnInfo(name = "id")
    private String plantId;
    private String name;
    private String description;
    private int growZoneNumber;
    private int wateringInterval = 7; //默认浇水间隔，单位——天
    private String imageUrl;

    public boolean shouldBeWatered(Calendar since, Calendar lastWateringDate){
        lastWateringDate.add(Calendar.DAY_OF_YEAR, wateringInterval);
        if (since.get(Calendar.DAY_OF_YEAR) > lastWateringDate.get(Calendar.DAY_OF_YEAR)){
            return true;
        } else {
            return false;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public String getPlantId() {
        return plantId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
