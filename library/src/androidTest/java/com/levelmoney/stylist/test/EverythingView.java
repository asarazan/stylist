package com.levelmoney.stylist.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.levelmoney.stylist.Stylist;
import com.levelmoney.stylist.annotations.StyleBoolean;
import com.levelmoney.stylist.annotations.StyleColor;
import com.levelmoney.stylist.annotations.StyleDimen;
import com.levelmoney.stylist.annotations.StyleFloat;
import com.levelmoney.stylist.annotations.StyleInteger;
import com.levelmoney.stylist.annotations.StyleResource;
import com.levelmoney.stylist.annotations.StyleString;

/**
 * Created by Aaron Sarazan on 5/20/15
 * Copyright(c) 2015 Level, Inc.
 */
public class EverythingView extends View {

    private static final String TAG = EverythingView.class.getSimpleName();

    @StyleBoolean(R.styleable.EverythingView_testBoolean)
    public Boolean testBoolean;

    @StyleInteger(R.styleable.EverythingView_testInteger)
    public Integer testInteger;

    @StyleInteger(R.styleable.EverythingView_testLong)
    public Long testLong;

    @StyleFloat(R.styleable.EverythingView_testFloat)
    public Float testFloat;

    @StyleFloat(R.styleable.EverythingView_testDouble)
    public Double testDouble;

    @StyleString(R.styleable.EverythingView_testString)
    public String testString;

    @StyleColor(R.styleable.EverythingView_testColor)
    public Integer testColor;

    @StyleDimen(R.styleable.EverythingView_testDimen)
    public Float testDimen;

    @StyleResource(R.styleable.EverythingView_testResource)
    public Integer testResource;

    public EverythingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Stylist.inject(this, attrs, R.styleable.EverythingView);
    }
}