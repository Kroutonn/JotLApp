package com.example.jotlapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.jotlapp.R;

public class DemolitionistFragment extends Fragment {

    public DemolitionistFragment() {

    }

    public static DemolitionistFragment newInstance() {
        DemolitionistFragment fragment = new DemolitionistFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_demolitionist, container, false);
    }
}
