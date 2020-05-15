package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.adapters.PlantAdapter;
import com.example.myapplication.data.entity.Plant;
import com.example.myapplication.databinding.FragmentPlantListBinding;
import com.example.myapplication.utilities.InjectorUtils;
import com.example.myapplication.viewmodels.GardenPlantingListViewModel;
import com.example.myapplication.viewmodels.GardenPlantingListViewModelFactory;
import com.example.myapplication.viewmodels.PlantListViewModel;
import com.example.myapplication.viewmodels.PlantListViewModelFactory;

import java.util.List;

public class PlantListFragment extends Fragment {

    private PlantListViewModelFactory factory;

    private PlantListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentPlantListBinding binding = DataBindingUtil
                .inflate( inflater, R.layout.fragment_plant_list, container, false);
        factory = InjectorUtils.providePlantListViewModelFactory(this);
        viewModel = new ViewModelProvider(this, factory).get(PlantListViewModel.class);

        if (getContext() == null)
            return binding.getRoot();

        PlantAdapter adapter = new PlantAdapter();
        binding.plantList.setAdapter(adapter);
        subscribeUi(adapter);

        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    private void subscribeUi(final PlantAdapter adapter) {
        viewModel.getPlants().observe(getViewLifecycleOwner(), new Observer<List<Plant>>() {
            @Override
            public void onChanged(List<Plant> plants) {
                adapter.submitList(plants);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filter_zone){
            updateData();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateData() {
        if (viewModel.isFiltered()) {
            viewModel.clearGrowZoneNumber();
        } else {
            viewModel.setGrowZoneNumber(9);
        }
    }
}
