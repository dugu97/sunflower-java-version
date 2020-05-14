package com.example.myapplication.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.myapplication.data.entity.Plant;

public class PlantDiffCallback extends DiffUtil.ItemCallback<Plant> {
    @Override
    public boolean areItemsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return oldItem.getPlantId().equals(newItem.getPlantId());
    }

    @Override
    public boolean areContentsTheSame(@NonNull Plant oldItem, @NonNull Plant newItem) {
        return false;
    }
}
