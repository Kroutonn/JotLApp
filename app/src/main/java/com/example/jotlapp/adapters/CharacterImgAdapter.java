package com.example.jotlapp.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.jotlapp.fragments.DemolitionistFragment;
import com.example.jotlapp.fragments.HatchetFragment;
import com.example.jotlapp.fragments.RedGuardFragment;
import com.example.jotlapp.fragments.VoidwardenFragment;

public class CharacterImgAdapter extends FragmentStateAdapter {
    public CharacterImgAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new VoidwardenFragment();
            case 1:
                return new RedGuardFragment();
            case 2:
                return new DemolitionistFragment();
            default:
                return new HatchetFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
