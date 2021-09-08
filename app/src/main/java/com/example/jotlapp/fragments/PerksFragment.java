package com.example.jotlapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.jotlapp.PlayerSheetActivity;
import com.example.jotlapp.R;
import com.example.jotlapp.adapters.PerkRecyclerAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.persistence.HeroRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private ArrayList<String> mPerkDescriptionList;
    private ArrayList<Perk> mPerkList;
    private PlayerSheetActivity mPlayerSheetActivity;
    private HeroRepository mHeroRepository;

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

        mPerkDescriptionList = new ArrayList<>();
        mPerkList = new ArrayList<>();
        mPlayerSheetActivity = (PlayerSheetActivity) getActivity();
        mHero = mPlayerSheetActivity.getHero();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perks, container, false);
        mHeroRepository = new HeroRepository(mPlayerSheetActivity);

        initRecyclerView(view);
        buildPerkList();

        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.FragmentPerks_RecyclerView_PerkList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        mPerkRecyclerAdapter = new PerkRecyclerAdapter(mPlayerSheetActivity, mPerkList, mHero,this );
        recyclerView.setAdapter(mPerkRecyclerAdapter);
    }

    private void buildPerkList() {
        mHeroRepository.getPerksTask(mHero.getCharacter()).observe(mPlayerSheetActivity, new Observer<List<Perk>>() {
                    @Override
                    public void onChanged(List<Perk> perks) {

                        mPerkList.addAll(perks);

                        mPerkRecyclerAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onItemClick(int selectedPosition) {
        int selectedPerkID = mPerkList.get(selectedPosition).getPerkId();

        mHeroRepository.addHeroPerkCrossRef(new HeroPerkCrossRef(mHero.getHeroId(), selectedPerkID));
    }
}