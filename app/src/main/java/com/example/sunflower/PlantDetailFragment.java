package com.example.sunflower;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnScrollChangeListener;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar.OnMenuItemClickListener;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ShareCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.sunflower.data.entity.Plant;
import com.example.sunflower.databinding.FragmentPlantDetailBinding;
import com.example.sunflower.utilities.InjectorUtils;
import com.example.sunflower.viewmodels.PlantDetailViewModel;
import com.example.sunflower.viewmodels.PlantDetailViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton.Behavior;
import com.google.android.material.snackbar.Snackbar;

public class PlantDetailFragment extends Fragment {

    private PlantDetailViewModelFactory factory;


    private PlantDetailViewModel plantDetailViewModel;

    {
//        factory = InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), args.plantId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final FragmentPlantDetailBinding binding = DataBindingUtil
            .inflate( inflater, R.layout.fragment_plant_detail, container, false);

        String plantId = null;
        if (getArguments() != null) {
            plantId = PlantDetailFragmentArgs.fromBundle(getArguments()).getPlantId();
        }

        factory = InjectorUtils.providePlantDetailViewModelFactory(requireActivity(), plantId);
        plantDetailViewModel = new ViewModelProvider(this, factory).get(PlantDetailViewModel.class);

        binding.setViewModel(plantDetailViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setCallback(new Callback() {
            @Override
            public void add(Plant plant) {
                hideAppBarFab(binding.fab);
                plantDetailViewModel.addPlantToGarden();
                Snackbar.make(binding.getRoot(), R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                        .show();
            }
        });


            // scroll change listener begins at Y = 0 when image is fully collapsed
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            binding.plantDetailScrollview.setOnScrollChangeListener(
                    new OnScrollChangeListener() {
                        @Override
                        public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                            // User scrolled past image to height of toolbar and the title text is
                            // underneath the toolbar, so the toolbar should be shown.
                            boolean shouldShowToolbar = scrollY > binding.toolbar.getHeight();

                            boolean isToolbarShown = false;
                            // The new state of the toolbar differs from the previous state; update
                            // appbar and toolbar attributes.
                            if (isToolbarShown != shouldShowToolbar) {
                                isToolbarShown = shouldShowToolbar;

                                // Use shadow animator to add elevation if toolbar is shown
                                binding.appbar.setActivated(shouldShowToolbar);

                                // Show the plant name if toolbar is shown
                                binding.toolbarLayout.setTitleEnabled(shouldShowToolbar);
                            }
                        }
                    }
            );
        }

        binding.toolbar.setNavigationOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigateUp();
                }
        });

        binding.toolbar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() ==  R.id.action_share){
                        createShareIntent();
                        return true;
                    }
                    return false;
                }
        });

        setHasOptionsMenu(true);

        return binding.getRoot();
    }

    // Helper function for calling a share functionality.
    // Should be used when user presses a share button/menu item.
    private void createShareIntent() {
        Plant plant = plantDetailViewModel.getPlant().getValue();
        String shareText;
        if (plant == null) {
            shareText = "";
        } else {
            shareText = getString(R.string.share_text_plant, plant.getName());
        }

        Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                .setText(shareText)
                .setType("text/plain")
                .createChooserIntent()
                .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        startActivity(shareIntent);
    }

    // FloatingActionButtons anchored to AppBarLayouts have their visibility controlled by the scroll position.
    // We want to turn this behavior off to hide the FAB when it is clicked.
    //
    // This is adapted from Chris Banes' Stack Overflow answer: https://stackoverflow.com/a/41442923
    private void hideAppBarFab(FloatingActionButton fab) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams)fab.getLayoutParams();
        Behavior behavior = (FloatingActionButton.Behavior)params.getBehavior();
        behavior.setAutoHideEnabled(false);
        fab.hide();
    }

    public interface Callback {
        void add(Plant plant);
    }
}
