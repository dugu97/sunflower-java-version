package com.example.sunflower.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.AbstractSavedStateViewModelFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.savedstate.SavedStateRegistryOwner;

import com.example.sunflower.data.repo.PlantRepository;

public class PlantListViewModelFactory extends AbstractSavedStateViewModelFactory {

    private PlantRepository repository;

    public PlantListViewModelFactory(@NonNull SavedStateRegistryOwner owner, PlantRepository repository) {
        super(owner, null);
        this.repository = repository;
    }

    @NonNull
    @Override
    protected <T extends ViewModel> T create(@NonNull String key, @NonNull Class<T> modelClass, @NonNull SavedStateHandle handle) {
        return (T) new PlantListViewModel(repository, handle);
    }
}
