package com.example.jotlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.jotlapp.adapters.CharacterImgAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.persistence.HeroRepository;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.Map;


public class CreateHeroActivity extends AppCompatActivity {

    private static final String TAG="CreateHeroActivity";

    // ui components
    private TabLayout mTabLayout;
    private ViewPager2 mPager;
    private CharacterImgAdapter mAdapter;
    private EditText mNameEditText;
    private Button mCreateButton;
    private ImageButton mBackButton;

    // vars
    private int mCurrentTab;
    private HeroRepository mHeroRepository;
    private Hero mNewHero;

    private static final Map<Integer, String> CLASS_MAP;
    static {
        CLASS_MAP = new HashMap<>();
        CLASS_MAP.put(0, "Voidwarden");
        CLASS_MAP.put(1, "Red Guard");
        CLASS_MAP.put(2, "Demolitionist");
        CLASS_MAP.put(3, "Hatchet");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_hero);
        mNameEditText = findViewById(R.id.ActivityCreateHero_EditText_Name);
        mTabLayout = findViewById(R.id.ActivityCreateHero_TabLayout_Characters);
        mPager = findViewById(R.id.ActivityCreateHero_ViewPager_CharImages);
        mNewHero = new Hero();

        mHeroRepository = new HeroRepository(this);

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new CharacterImgAdapter(fm, getLifecycle());
        mPager.setAdapter(mAdapter);

        initTabs();
        initButtons();

    }

    private void initTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Voidwarden"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Red Guard"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Demolitionist"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Hatchet"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentTab = tab.getPosition();
                mPager.setCurrentItem(mCurrentTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });

        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
    }

    private void initButtons() {
        mCreateButton = findViewById(R.id.ActivityCreateHero_Button_Create);
        mBackButton = findViewById(R.id.LayoutCreateHeroToolbar_ImageButton_Back);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateHeroActivity.this, PlayerSheetActivity.class);
                addNewHero();
                //intent.putExtra("selected_hero", mNewHero);
                //startActivity(intent);
                finish();
            }
        });

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateHeroActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    private void addNewHero() {

        if (mNameEditText.length() != 0) {
            mNewHero.setName(mNameEditText.getText().toString());
            mNewHero.setCharacter(CLASS_MAP.get(mCurrentTab));
            mNewHero.setExperiance("0");
            mNewHero.setGold("0");
            mNewHero.setLevel("1");
            mNewHero.setNotes(0);

            mNameEditText.setText("");

            mHeroRepository.insertHeroTask(mNewHero);
        } else {
            Toast.makeText(CreateHeroActivity.this, "You must name your character!", Toast.LENGTH_LONG).show();
        }
    }
}