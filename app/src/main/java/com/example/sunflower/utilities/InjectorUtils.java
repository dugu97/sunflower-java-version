package com.example.sunflower.utilities;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.sunflower.data.AppDatabase;
import com.example.sunflower.data.repo.GardenPlantingRepository;
import com.example.sunflower.data.repo.PlantRepository;
import com.example.sunflower.viewmodels.GardenPlantingListViewModelFactory;
import com.example.sunflower.viewmodels.PlantDetailViewModelFactory;
import com.example.sunflower.viewmodels.PlantListViewModelFactory;

public class InjectorUtils {
    public static PlantRepository getPlantRepository(Context context) {
        return PlantRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).plantDao());
    }

    public static GardenPlantingRepository getGardenPlantingRepository(Context context){
        return GardenPlantingRepository.getInstance(
                AppDatabase.getInstance(context.getApplicationContext()).gardenPlantingDao());
    }

    public static GardenPlantingListViewModelFactory provideGardenPlantingListViewModelFactory(Context context) {
        GardenPlantingRepository repository = getGardenPlantingRepository(context);
        return new GardenPlantingListViewModelFactory(repository);
    }

    public static PlantListViewModelFactory providePlantListViewModelFactory(Fragment fragment) {
        PlantRepository repository = getPlantRepository(fragment.requireContext());
        return new PlantListViewModelFactory(fragment, repository);
    }

    public static PlantDetailViewModelFactory providePlantDetailViewModelFactory(Context context, String plantId) {
        return new PlantDetailViewModelFactory(getPlantRepository(context),
                getGardenPlantingRepository(context), plantId);
    }
}
