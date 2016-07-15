package com.leonidov.testdisplayheight;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Display";

    private Button mOpenKeyboardBtn;
    private Button mHideKeyboardBtn;

    private TextView mTvPointSize;
    private TextView mTvRectSize;
    private TextView mTvDisplayMetrics;

    private TextView mTvRealPointSize;
    private TextView mTvRealDisplayMetrics;

    private DisplayUtils mDisplayUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWindowSettings();

        setContentView(R.layout.activity_main);

        mDisplayUtils = new DisplayUtils();

        mOpenKeyboardBtn = (Button) findViewById(R.id.btnOpenKeyboard);
        mHideKeyboardBtn = (Button) findViewById(R.id.btnHideKeyboard);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                switch (view.getId()) {
                    case R.id.btnOpenKeyboard:
                        imm.toggleSoftInputFromWindow(view.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
                        break;
                    case R.id.btnHideKeyboard:
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        break;
                }

                updateViews();
            }
        };
        mOpenKeyboardBtn.setOnClickListener(onClickListener);
        mHideKeyboardBtn.setOnClickListener(onClickListener);

        mTvPointSize = (TextView) findViewById(R.id.tvPoint);
        mTvRectSize = (TextView) findViewById(R.id.tvRect);
        mTvDisplayMetrics = (TextView) findViewById(R.id.tvDispMetrics);

        mTvRealPointSize = (TextView) findViewById(R.id.tvRealPoint);
        mTvRealDisplayMetrics = (TextView) findViewById(R.id.tvRealDispMetrics);

        updateViews();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        updateViews();
    }

    private void initWindowSettings() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
    }

    private void updateViews() {
        mDisplayUtils.updateParams(this);

        Point outPoint = mDisplayUtils.getSize();
        String point = String.format("Point size: point.x = %d; point y = %d", outPoint.x, outPoint.y);
        Log.i(TAG, point);
        mTvPointSize.setText(point);

        Rect outRect = mDisplayUtils.getRectSize();
        String rect = String.format("Rect size: rect.top = %d; rect.left = %d; rect.bottom = %d; rect.right = %d", outRect.top, outRect.left, outRect.bottom, outRect.right);
        Log.i(TAG, rect);
        mTvRectSize.setText(rect);

        DisplayMetrics outDisplayMetrics = mDisplayUtils.getMetrics();
        String displayMetrics = String.format("Display Metric: width = %d; height = %d", outDisplayMetrics.widthPixels, outDisplayMetrics.heightPixels);
        Log.i(TAG, displayMetrics);
        mTvDisplayMetrics.setText(displayMetrics);


        //REAL
        Point outRealPoint = mDisplayUtils.getRealSize();
        String realPoint = String.format("Real size: point.x = %d; point.y = %d", outRealPoint.x, outRealPoint.y);
        Log.d(TAG, realPoint);
        mTvRealPointSize.setText(realPoint);

        DisplayMetrics outRealDisplayMetrics = mDisplayUtils.getRealMetrics();
        String realDisplayMetrics = String.format("Real Display Metric: width = %d; height = %d", outRealDisplayMetrics.widthPixels, outRealDisplayMetrics.heightPixels);
        Log.d(TAG, realDisplayMetrics);
        mTvRealDisplayMetrics.setText(realDisplayMetrics);
    }
}
