package com.example.jotlapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jotlapp.PlayerSheetActivity;
import com.example.jotlapp.R;
import com.example.jotlapp.adapters.PerkRecyclerAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerksFragment extends Fragment implements PerkRecyclerAdapter.ItemClickListener {

    // ui components
    private RecyclerView mRecyclerView;

    //vars
    private PerkRecyclerAdapter mPerkRecyclerAdapter;
    private Hero mHero;
    private ArrayList<String> mPerkList;
    private PlayerSheetActivity playerSheetActivity;

    public PerksFragment() {
        // Required empty public constructor
    }

    public static PerksFragment newInstance() {
        PerksFragment fragment = new PerksFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPerkList = new ArrayList<>();
        playerSheetActivity = (PlayerSheetActivity) getActivity();
        mHero = playerSheetActivity.getHero();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perks, container, false);

        buildPerkList();
        initRecyclerView(view);

        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.FragmentPerks_RecyclerView_PerkList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        PerkRecyclerAdapter adapter = new PerkRecyclerAdapter(mPerkList, this );
        recyclerView.setAdapter(adapter);
    }

    private void buildPerkList() {
        ArrayList<String> perkList;

        switch (mHero.getCharacter()) {
            case "Voidwarden" :
                mPerkList.addAll(Arrays.asList(getResources().getStringArray(R.array.voidwarden)));
                break;
            case "Red Guard" :
                mPerkList.addAll(Arrays.asList(getResources().getStringArray(R.array.red_guard)));
                break;
            case "Demolitionist" :
                mPerkList.addAll(Arrays.asList(getResources().getStringArray(R.array.demolitionist)));
                break;
            case "Hatchet" :
                mPerkList.addAll(Arrays.asList(getResources().getStringArray(R.array.hatchet)));
                break;
        }
    }

    @Override
    public void onItemClick(String perk) {

    }
}