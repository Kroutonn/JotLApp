package com.example.jotlapp.util;

import android.text.InputFilter;
import android.text.Spanned;

public class MinMaxFilter implements InputFilter {
    private int mIntMin;
    private int mIntMax;

    public MinMaxFilter(int minValue, int maxValue) {
        this.mIntMin = minValue;
        this.mIntMax = maxValue;
    }

    public MinMaxFilter(String minValue, String maxValue) {
        this.mIntMin = Integer.parseInt(minValue);
        this.mIntMax = Integer.parseInt(maxValue);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange( mIntMin, mIntMax, input)) return null;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    private boolean isInRange(int min, int max, int checkedValue) {
        return max > min
                ? checkedValue >= min && checkedValue <= max //true
                : checkedValue >= max && checkedValue <= min; //false
    }
}

