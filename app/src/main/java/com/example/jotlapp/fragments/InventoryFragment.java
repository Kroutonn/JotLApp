package com.example.jotlapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.jotlapp.R;
import com.example.jotlapp.adapters.InventoryRecyclerAdapter;
import com.example.jotlapp.adapters.PerkRecyclerAdapter;
import com.example.jotlapp.models.Item;
import com.example.jotlapp.persistence.HeroRepository;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InventoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryFragment extends Fragment {

    // ui components
    private EditText mItemEditText;
    private Button mAddButton;
    private RecyclerView mRecyclerView;

    // vars
    ArrayList<String> mItemList;
    private HeroRepository mHeroRepository;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inventory, container, false);

        mItemEditText = view.findViewById(R.id.FragmentInventory_EditText_Item);
        mAddButton = view.findViewById(R.id.FragmentInventory_Button_Add);
        mItemList = new ArrayList<>();
        mHeroRepository = new HeroRepository(view.getContext());

        buildDummyList();
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
        RecyclerView recyclerView = view.findViewById(R.id.FragmentInventory_RecyclerView_Items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        InventoryRecyclerAdapter adapter = new InventoryRecyclerAdapter(mItemList);
        recyclerView.setAdapter(adapter);
    }

    private void buildInventoryList() {

    }

    private void addItem() {
        Item newItem = new Item(mItemEditText.getText().toString());
    }

    private void buildDummyList() {
        mItemList.add("Item 1");
        mItemList.add("Item 2");
        mItemList.add("Item 3");
        mItemList.add("Item 4");
        mItemList.add("Item 5");
        mItemList.add("Item 6");
        mItemList.add("Item 7");
        mItemList.add("Item 8");
        mItemList.add("Item 9");
        mItemList.add("Item 10");
        mItemList.add("Item 11");
    }
}