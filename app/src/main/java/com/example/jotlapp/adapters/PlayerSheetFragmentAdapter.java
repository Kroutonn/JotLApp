package com.example.jotlapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jotlapp.fragments.DemolitionistFragment;
import com.example.jotlapp.fragments.HatchetFragment;
import com.example.jotlapp.fragments.InventoryFragment;
import com.example.jotlapp.fragments.PerksFragment;
import com.example.jotlapp.fragments.RedGuardFragment;
import com.example.jotlapp.fragments.VoidwardenFragment;

public class PlayerSheetFragmentAdapter extends FragmentStateAdapter {

    public PlayerSheetFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position == 0) {
            return new InventoryFragment();
        } else {
            return new PerksFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
