package com.example.sunflower.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.sunflower.data.vo.PlantAndGardenPlantings;

public class GardenPlantDiffCallback extends DiffUtil.ItemCallback<PlantAndGardenPlantings>{

        @Override
        public boolean areItemsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.getPlant().getPlantId().equals(newItem.getPlant().getPlantId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlantAndGardenPlantings oldItem, @NonNull PlantAndGardenPlantings newItem) {
            return oldItem.getPlant().equals(newItem.getPlant());
        }
}