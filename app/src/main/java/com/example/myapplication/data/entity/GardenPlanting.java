package com.example.myapplication.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.myapplication.data.entity.Plant;

import java.util.Calendar;

@Entity(tableName = "garden_plantings",
        foreignKeys = {@ForeignKey(entity = Plant.class, parentColumns = "id", childColumns = "plant_id")},
        indices = @Index(value = "plant_id"))
public class GardenPlanting {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long gardenPlantingId;

    @ColumnInfo(name = "plant_id")
    private String plantId;

    /**
     * Indicates when the [Plant] was planted. Used for showing notification when it's time
     * to harvest the plant.
     */
    @ColumnInfo(name = "plant_date")
    private Calendar plantDate = Calendar.getInstance();

    /**
     * Indicates when the [Plant] was last watered. Used for showing notification when it's
     * time to water the plant.
     */
    @ColumnInfo(name = "last_watering_date")
    private Calendar lastWateringDate = Calendar.getInstance();

    public GardenPlanting(String plantId) {
        this.plantId = plantId;
    }

    public long getGardenPlantingId() {
        return gardenPlantingId;
    }

    public String getPlantId() {
        return plantId;
    }

    public Calendar getPlantDate() {
        return plantDate;
    }

    public Calendar getLastWateringDate() {
        return lastWateringDate;
    }
}
