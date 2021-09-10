package com.example.jotlapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.jotlapp.PlayerSheetActivity;
import com.example.jotlapp.R;
import com.example.jotlapp.adapters.InventoryRecyclerAdapter;
import com.example.jotlapp.adapters.PerkRecyclerAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.models.Item;
import com.example.jotlapp.models.Perk;
import com.example.jotlapp.models.relations.HeroItemCrossRef;
import com.example.jotlapp.models.relations.HeroPerkCrossRef;
import com.example.jotlapp.models.relations.HeroWithItem;
import com.example.jotlapp.persistence.HeroRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {

    // ui components
    private AutoCompleteTextView mAutoCompleteTextView;
    private Button mAddButton;
    private RecyclerView mRecyclerView;

    // vars
    private ArrayList<String> mAutocompleteItemList;
    private List<Item> mAllItemsList;
    private ArrayList<Item> mHeroItemList;
    private HeroRepository mHeroRepository;
    private InventoryRecyclerAdapter mItemRecyclerAdapter;
    private PlayerSheetActivity mPlayerSheetActivity;
    private Hero mHero;
    private GestureDetector mGestureDetector;

    public InventoryFragment() {
        // Required empty public constructor
    }

    public static InventoryFragment newInstance() {
        InventoryFragment fragment = new InventoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayerSheetActivity = (PlayerSheetActivity) getActivity();
        mHero = mPlayerSheetActivity.getHero();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);
        mHeroRepository = new HeroRepository(view.getContext());
        mAllItemsList = mHeroRepository.retrieveItemsTask();

        ArrayList<String> itemNames = new ArrayList<String>();
        for (Item item : mAllItemsList) {
            itemNames.add(item.getName());
        }

        mHeroItemList = new ArrayList<>();

        mAutoCompleteTextView = view.findViewById(R.id.FragmentInventory_AutoCompleteTextView_Item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                android.R.layout.simple_list_item_1, itemNames);
        mAutoCompleteTextView.setAdapter(adapter);

        mAddButton = view.findViewById(R.id.FragmentInventory_Button_Add);
        mAutocompleteItemList = new ArrayList<>();


        buildInventoryList();
        initRecyclerView(view);

        mAddButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addItem();
                    }
                }
        );

        return view;
    }

    private void initRecyclerView(View view) {
        mRecyclerView = view.findViewById(R.id.FragmentInventory_RecyclerView_Items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mItemRecyclerAdapter = new InventoryRecyclerAdapter(mHeroItemList);
        mRecyclerView.setAdapter(mItemRecyclerAdapter);
    }

    private void buildInventoryList() {
        mHeroRepository.getItemsForHero(mHero.getHeroId()).observe(getViewLifecycleOwner(), new Observer<List<HeroWithItem>>() {

            @Override
            public void onChanged(List<HeroWithItem> items) {
                if (mHeroItemList.size() > 0) {
                    mHeroItemList.clear();
                }
                if(items != null) {
                    mHeroItemList.addAll(items.get(0).getItems());
                }

                mItemRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addItem() {
        Item itemId = mHeroRepository.getItemObject(mAutoCompleteTextView.getText().toString());

        mHeroRepository.addHeroItemCrossRef(new HeroItemCrossRef(mHero.getHeroId(), itemId.getItemId()));

    }
}