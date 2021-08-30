package com.example.jotlapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.jotlapp.R;

public class HatchetFragment extends Fragment {

    public HatchetFragment() {

    }

    public static HatchetFragment newInstance() {
        HatchetFragment fragment = new HatchetFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hatchet, container, false);
    }
}
