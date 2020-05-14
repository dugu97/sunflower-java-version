package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.myapplication.HomeViewPagerFragmentDirections;
import com.example.myapplication.HomeViewPagerFragmentDirections.ActionViewPagerFragmentToPlantDetailFragment;
import com.example.myapplication.data.entity.Plant;
import com.example.myapplication.data.vo.PlantAndGardenPlantings;
import com.example.myapplication.databinding.ListItemGardenPlantingBinding;
import com.example.myapplication.databinding.ListItemPlantBinding;
import com.example.myapplication.viewmodels.PlantAndGardenPlantingsViewModel;

public class PlantAdapter extends ListAdapter<Plant, PlantAdapter.PlantViewHolder> {


    public PlantAdapter() {
        super(new PlantDiffCallback());
    }

    @NonNull
    @Override
    public PlantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlantViewHolder(ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlantViewHolder holder, int position) {
        Plant plant = getItem(position);
        holder.bind(plant);
    }

    class PlantViewHolder extends RecyclerView.ViewHolder {

        private ListItemPlantBinding binding;

        public PlantViewHolder(@NonNull final ListItemPlantBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToPlant(binding.getPlant(), v);
                }
            });
        }

        private void navigateToPlant(Plant plant, View view) {
            ActionViewPagerFragmentToPlantDetailFragment direction = HomeViewPagerFragmentDirections.actionViewPagerFragmentToPlantDetailFragment(
                            plant.getPlantId());
            Navigation.findNavController(view).navigate(direction);
        }


        void bind(Plant item) {
            binding.setPlant(item);
            binding.executePendingBindings();
        }
    }
}
