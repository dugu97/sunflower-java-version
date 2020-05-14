package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.HomeViewPagerFragmentDirections;
import com.example.myapplication.HomeViewPagerFragmentDirections.ActionViewPagerFragmentToPlantDetailFragment;
import com.example.myapplication.R;
import com.example.myapplication.data.vo.PlantAndGardenPlantings;
import com.example.myapplication.databinding.ListItemGardenPlantingBinding;
import com.example.myapplication.viewmodels.PlantAndGardenPlantingsViewModel;

public class GardenPlantingAdapter extends ListAdapter<PlantAndGardenPlantings, GardenPlantingAdapter.ViewHolder> {

    public GardenPlantingAdapter() {
        super(new GardenPlantDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((ListItemGardenPlantingBinding) DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_garden_planting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ListItemGardenPlantingBinding binding;

        public ViewHolder(@NonNull final ListItemGardenPlantingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.setClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigateToPlant(binding.getViewModel().getPlantId(), v);
                }
            });
        }

        private void navigateToPlant(String plantId,View view) {
            ActionViewPagerFragmentToPlantDetailFragment direction = HomeViewPagerFragmentDirections
                    .actionViewPagerFragmentToPlantDetailFragment(plantId);
            Navigation.findNavController(view).navigate(direction);
        }

        void bind(PlantAndGardenPlantings plantings) {
            binding.setViewModel(new PlantAndGardenPlantingsViewModel(plantings));
            binding.executePendingBindings();
        }
    }
}

