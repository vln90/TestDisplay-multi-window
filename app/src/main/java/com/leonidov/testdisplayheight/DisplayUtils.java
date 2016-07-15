package com.leonidov.testdisplayheight;


import android.app.Activity;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;

public class DisplayUtils {

    private Display mDisplay;

    public void updateParams(Activity context) {
        mDisplay = context.getWindowManager().getDefaultDisplay();
    }

    public Point getSize() {
        Point point = new Point();
        mDisplay.getSize(point);

        return point;
    }

    public Rect getRectSize() {
        Rect rect = new Rect();
        mDisplay.getRectSize(rect);

        return rect;
    }

    public DisplayMetrics getMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mDisplay.getMetrics(displayMetrics);

        return displayMetrics;
    }

    public Point getRealSize() {
        Point point = new Point();
        mDisplay.getRealSize(point);

        return point;
    }

    public DisplayMetrics getRealMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        mDisplay.getRealMetrics(displayMetrics);

        return displayMetrics;
    }
}
