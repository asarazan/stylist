package com.levelmoney.stylist.test;

import android.graphics.Color;
import android.test.AndroidTestCase;
import android.view.LayoutInflater;


/**
 * Created by Aaron Sarazan on 5/20/15
 * Copyright(c) 2015 Level, Inc.
 */
public class StylistTests extends AndroidTestCase {

    public void testEmpty() {
        EverythingView view = (EverythingView) LayoutInflater.from(getContext()).inflate(R.layout.empty, null);
        assertNull(view.testBoolean);
        assertNull(view.testInteger);
        assertNull(view.testLong);
        assertNull(view.testFloat);
        assertNull(view.testDouble);
        assertNull(view.testString);
        assertNull(view.testColor);
        assertNull(view.testDimen);
        assertNull(view.testResource);
    }

    public void testDirect() {
        EverythingView view = (EverythingView) LayoutInflater.from(getContext()).inflate(R.layout.direct, null);
        assertEquals(true,              (boolean) view.testBoolean);
        assertEquals(1,                 (int) view.testInteger);
        assertEquals(1L,                (long) view.testLong);
        assertEquals(1f,                view.testFloat);
        assertEquals(1.0,               view.testDouble);
        assertEquals("aString",         view.testString);
        assertEquals(Color.WHITE,       (int) view.testColor);
        assertEquals(1f,                view.testDimen);
        assertEquals(R.string.app_name, (int) view.testResource);
    }

    public void testReferences() {
        EverythingView view = (EverythingView) LayoutInflater.from(getContext()).inflate(R.layout.references, null);
        assertEquals(true,              (boolean) view.testBoolean);
        assertEquals(1,                 (int) view.testInteger);
        assertEquals("stylist",         view.testString);
        assertEquals(Color.WHITE,       (int) view.testColor);
        assertEquals(1f,                view.testDimen);
        assertEquals(R.string.app_name, (int) view.testResource);
    }

}
