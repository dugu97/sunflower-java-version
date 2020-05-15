package com.example.sunflower.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sunflower.GardenFragment;
import com.example.sunflower.PlantListFragment;

import java.util.HashMap;
import java.util.Map;

public class SunflowerPagerAdapter extends FragmentStateAdapter {

    public static final int MY_GARDEN_PAGE_INDEX = 0;
    public static final int PLANT_LIST_PAGE_INDEX = 1;

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private Map<Integer,Fragment> tabFragmentsCreators = new HashMap<>();
    {
        tabFragmentsCreators.put(MY_GARDEN_PAGE_INDEX, new GardenFragment());
        tabFragmentsCreators.put(PLANT_LIST_PAGE_INDEX, new PlantListFragment());
    }

    public SunflowerPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = tabFragmentsCreators.get(position);
        if (fragment != null){
            return fragment;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int getItemCount() {
        return tabFragmentsCreators.size();
    }
}
