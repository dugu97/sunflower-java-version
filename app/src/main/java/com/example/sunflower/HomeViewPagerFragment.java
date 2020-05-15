package com.example.sunflower;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sunflower.adapters.SunflowerPagerAdapter;
import com.example.sunflower.databinding.FragmentViewPagerBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.Tab;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy;

import static com.example.sunflower.adapters.SunflowerPagerAdapter.MY_GARDEN_PAGE_INDEX;
import static com.example.sunflower.adapters.SunflowerPagerAdapter.PLANT_LIST_PAGE_INDEX;

public class HomeViewPagerFragment extends Fragment {

    public HomeViewPagerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentViewPagerBinding binding = DataBindingUtil
                .inflate( inflater, R.layout.fragment_view_pager, container, false);
        TabLayout tabLayout = binding.tabs;
        ViewPager2 viewPager = binding.viewPager;

        viewPager.setAdapter(new SunflowerPagerAdapter(this));

        // Set the icon and text for each tab
        new TabLayoutMediator(tabLayout, viewPager, new TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull Tab tab, int position) {
                tab.setIcon(getTabIcon(position));
                tab.setText(getTabTitle(position));
            }
        }).attach();

        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);

        return binding.getRoot();
    }

    private int getTabIcon(int position) {
        if (position == MY_GARDEN_PAGE_INDEX){
            return R.drawable.garden_tab_selector;
        }else if (position == PLANT_LIST_PAGE_INDEX){
            return R.drawable.plant_list_tab_selector;
        }else {
            throw new IndexOutOfBoundsException();
        }
    }

    private String getTabTitle(int position) {
        if (position == MY_GARDEN_PAGE_INDEX){
            return getString(R.string.my_garden_title);
        }else if (position == PLANT_LIST_PAGE_INDEX){
            return getString(R.string.plant_list_title);
        }else {
            return null;
        }
    }
}
