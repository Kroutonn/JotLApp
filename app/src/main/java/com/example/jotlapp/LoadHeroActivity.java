package com.example.jotlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.jotlapp.adapters.HeroRecyclerAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.persistence.HeroRepository;
import com.example.jotlapp.util.VerticalSpacingItemDecorator;

import java.util.ArrayList;
import java.util.List;

public class LoadHeroActivity extends AppCompatActivity implements HeroRecyclerAdapter.OnHeroListener {

    private static final String TAG = "LoadHeroActivity";

    // UI components
    private RecyclerView mRecyclerView;
    private ImageButton mBackButton;

    // vars
    private ArrayList<Hero> mHeroes = new ArrayList<>();
    private HeroRecyclerAdapter mHeroRecyclerAdapter;
    private HeroRepository mHeroRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_hero);
        mRecyclerView = findViewById(R.id.ActivityLoadHero_RecyclerView_HeroList);
        mBackButton = findViewById(R.id.LayoutLoadHeroToolbarr_ImageButton_Back);

        mHeroRepository = new HeroRepository(this);
        initRecyclerView();
        retrieveHeroes();
        //insertFakeHeroes();

        setSupportActionBar((Toolbar) findViewById(R.id.ActivityLoadHero_Toolbar_Title));

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoadHeroActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void retrieveHeroes() {
        mHeroRepository.retrieveHeroesTask().observe(this, new Observer<List<Hero>>() {
            @Override
            public void onChanged(List<Hero> heroes) {
                if(mHeroes.size() > 0) {
                    mHeroes.clear();
                }
                if(heroes != null) {
                    mHeroes.addAll(heroes);
                }
                mHeroRecyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mHeroRecyclerAdapter = new HeroRecyclerAdapter(mHeroes, this, this);
        mRecyclerView.setAdapter(mHeroRecyclerAdapter);
    }

    @Override
    public void onHeroClick(int position) {
        Log.d(TAG, "onHeroClick: clicked." + position);

        Intent intent = new Intent(this, PlayerSheetActivity.class);
        intent.putExtra("selected_hero", mHeroes.get(position));
        startActivity(intent);
    }
}