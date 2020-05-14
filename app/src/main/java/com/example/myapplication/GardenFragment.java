package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.adapters.GardenPlantingAdapter;
import com.example.myapplication.data.vo.PlantAndGardenPlantings;
import com.example.myapplication.databinding.FragmentGardenBinding;
import com.example.myapplication.utilities.InjectorUtils;
import com.example.myapplication.viewmodels.GardenPlantingListViewModel;
import com.example.myapplication.viewmodels.GardenPlantingListViewModelFactory;

import java.util.List;

import static com.example.myapplication.adapters.SunflowerPagerAdapter.PLANT_LIST_PAGE_INDEX;

public class GardenFragment extends Fragment {

    private FragmentGardenBinding binding;

    private GardenPlantingListViewModelFactory factory;

    private GardenPlantingListViewModel viewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate( inflater, R.layout.fragment_garden, container, false);

        GardenPlantingAdapter adapter = new GardenPlantingAdapter();
        binding.gardenList.setAdapter(adapter);

        binding.addPlant.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToPlantListPage();
            }
        });

        subscribeUi(adapter, binding);
        return binding.getRoot();
    }

    private void subscribeUi(final GardenPlantingAdapter adapter, final FragmentGardenBinding binding) {
        factory = InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext());
        GardenPlantingListViewModel viewModel = new ViewModelProvider(this, factory).get(GardenPlantingListViewModel.class);

        viewModel.getPlantAndGardenPlantings().observe(getViewLifecycleOwner(), new Observer<List<PlantAndGardenPlantings>>() {
            @Override
            public void onChanged(List<PlantAndGardenPlantings> plantAndGardenPlantings) {
                binding.setHasPlantings(!plantAndGardenPlantings.isEmpty());
                adapter.submitList(plantAndGardenPlantings);
            }
        });
    }

    private void navigateToPlantListPage() {
        ViewPager2 viewPager2 = requireActivity().findViewById(R.id.view_pager);
        viewPager2.setCurrentItem(PLANT_LIST_PAGE_INDEX);
    }

}
