package com.example.jotlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jotlapp.adapters.PerkRecyclerAdapter;
import com.example.jotlapp.adapters.PlayerSheetFragmentAdapter;
import com.example.jotlapp.models.Hero;
import com.example.jotlapp.persistence.HeroRepository;
import com.example.jotlapp.util.MinMaxFilter;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

public class PlayerSheetActivity extends AppCompatActivity {

    private static final String TAG = "PlayerSheetActivity";

    // Ui components
    private TextView mNameTextView;
    private TextView mLevelTextView;
    private EditText mExpEditText;
    private EditText mGoldEditText;
    private ImageButton mExpSubtractImgButton;
    private ImageButton mExpAddImgButton;
    private ImageButton mGoldSubractImgButton;
    private ImageButton mGoldAddImgButton;
    private ImageButton mBackButton;
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;

    // vars
    private static final int MAX_VALUE = 999;
    private static final int MIN_VALUE = 0;
    private Hero mHero;
    private int mCurrentTab;
    private PlayerSheetFragmentAdapter mAdapter;
    private HeroRepository mHeroRepository;
    private String mLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_sheet);

        mNameTextView = findViewById(R.id.LayoutPlayerSheetToolbar_TextView_Name);
        mLevelTextView = findViewById(R.id.LayoutPlayerSheetToolbar_TextView_Level);
        mExpEditText = findViewById(R.id.ActivityPlayerSheet_EditText_Exp);
        mGoldEditText = findViewById(R.id.ActivityPlayerSheet_EditText_Gold);
        mExpSubtractImgButton = findViewById(R.id.ActivityPlayerSheet_ImageButton_SubtractExp);
        mExpAddImgButton = findViewById(R.id.ActivityPlayerSheet_ImageButton_AddExp);
        mGoldSubractImgButton = findViewById(R.id.ActivityPlayerSheet_ImageButton_SubtractGold);
        mGoldAddImgButton = findViewById(R.id.ActivityPlayerSheet_ImageButton_AddGold);
        mBackButton = findViewById(R.id.LayoutPlayerSheetToolbar_ImageButton_Back);
        mTabLayout = findViewById(R.id.ActivityPlayerSheet_TabLayout_Tabs);
        mViewPager = findViewById(R.id.ActivityPlayerSheet_ViewPager_Tabs);

        mExpEditText.setFilters(new InputFilter[]{ new MinMaxFilter( Integer.toString(MIN_VALUE), Integer.toString(MAX_VALUE) )});
        mGoldEditText.setFilters(new InputFilter[] { new MinMaxFilter( Integer.toString(MIN_VALUE), Integer.toString(MAX_VALUE) )});

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new PlayerSheetFragmentAdapter(fm, getLifecycle());
        mViewPager.setAdapter(mAdapter);

        mHeroRepository = new HeroRepository(this);

        initTabs();
        setupButtons();

        if (getIntent().hasExtra("selected_hero")) {
            mHero = getIntent().getParcelableExtra("selected_hero");
            Log.d(TAG, "onCreate: " + mHero.toString());

            setHeroProperties();
        }
    }

    private void initTabs() {
        mTabLayout.addTab(mTabLayout.newTab().setText("Inventory"));
        mTabLayout.addTab(mTabLayout.newTab().setText("Perks"));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCurrentTab = tab.getPosition();
                mViewPager.setCurrentItem(mCurrentTab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.selectTab(mTabLayout.getTabAt(position));
            }
        });
    }

    private void setHeroProperties() {
        mNameTextView.setText(mHero.getName());
        mLevelTextView.setText("Level " + mHero.getLevel() + " " + mHero.getCharacter());
        mExpEditText.setText(mHero.getExperiance());
        mGoldEditText.setText(mHero.getGold());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    Log.d("focus", "touchevent");
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }

        checkForChanges();
        return super.dispatchTouchEvent(event);
    }

    private void setupButtons() {
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mExpSubtractImgButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String oldValue = mHero.getExperiance();
                String newValue = subtractOne(oldValue);

                if (newValue != oldValue) {
                    mHero.setExperiance(newValue);
                    updateLevel(newValue);
                    updateHero();
                    mExpEditText.setText(newValue);
                }
            }
        });

        mExpAddImgButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String oldValue = mHero.getExperiance();
                String newValue = addOne(oldValue);

                if (newValue != oldValue) {
                    mHero.setExperiance(newValue);
                    updateLevel(newValue);
                    updateHero();
                    mExpEditText.setText(newValue);
                }
            }
        });

        mGoldSubractImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldValue = mHero.getGold();
                String newValue = subtractOne(oldValue);

                if (newValue != oldValue) {
                    mHero.setGold(newValue);
                    updateHero();
                    mGoldEditText.setText(newValue);
                }
            }
        });

        mGoldAddImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldValue = mHero.getGold();
                String newValue = addOne(oldValue);

                if (newValue != oldValue) {
                    mHero.setGold(newValue);
                    updateHero();
                    mGoldEditText.setText(newValue);
                }
            }
        });
    }

    private String subtractOne(String val) {
        int mVal = Integer.parseInt(val);

        if (mVal - 1 < MIN_VALUE) {
            return Integer.toString(MIN_VALUE);
        }

        return Integer.toString(mVal - 1);
    }

    private String addOne(String val) {
        int mVal = Integer.parseInt(val);

        if (mVal + 1 > MAX_VALUE) {
            return Integer.toString(MAX_VALUE);
        }

        return Integer.toString(mVal + 1);
    }

    private void updateHero() {
        mHeroRepository.updateHero(mHero);
    }

    private void checkForChanges() {
        String oldExpValue = mHero.getExperiance();
        String newExpValue = mExpEditText.getText().toString();
        String oldGoldValue = mHero.getGold();
        String newGoldValue = mGoldEditText.getText().toString();

        if (newExpValue != oldExpValue) {
            mHero.setExperiance(newExpValue);
            updateHero();
            if (updateLevel(newExpValue)) {
                mLevelTextView.setText("Level " + mHero.getLevel() + " " + mHero.getCharacter());
            }
        }

        if (newGoldValue != oldGoldValue) {
            mHero.setGold(newGoldValue);
            updateHero();
        }
    }

    private boolean updateLevel(String experiance) {
        int exp = Integer.parseInt(experiance);

        if (exp < 45 && mLevel != "1") {
            mHero.setLevel("1");
            return true;
        } else if (mLevel != "2" && exp < 95 && exp >= 45) {
            mHero.setLevel("2");
            return true;
        } else if (mLevel != "3" && exp < 150 && exp >= 95) {
            mHero.setLevel("3");
            return true;
        } else if (mLevel != "4" && exp < 210 && exp >= 150) {
            mHero.setLevel("4");
            return true;
        } else if (mLevel != "5" && exp < 275 && exp >= 210) {
            mHero.setLevel("5");
            return true;
        } else if (mLevel != "6" && exp < 345 && exp >= 275) {
            mHero.setLevel("6");
            return true;
        } else if (mLevel != "7" && exp < 420 && exp >= 345) {
            mHero.setLevel("7");
            return true;
        } else if (mLevel != "8" && exp < 500 && exp >= 420) {
            mHero.setLevel("8");
            return true;
        } else if (mLevel != "9" && exp >= 500) {
            mHero.setLevel("9");
            return true;
        }

        return false;
    }

    public Hero getHero() {
        return mHero;
    }
}