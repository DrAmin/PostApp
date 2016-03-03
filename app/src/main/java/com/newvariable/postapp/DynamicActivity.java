package com.newvariable.postapp;

import android.app.ActionBar;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Html;
import android.text.Layout;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.wefika.flowlayout.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepa on 19/02/2016.
 */
public class DynamicActivity extends AppCompatActivity {
    LinearLayout layout;
    Button bt_add,bt_temp;
    int x;
    String path;
    List<String> data = new ArrayList<>();

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample);
        layout = (LinearLayout) findViewById(R.id.ll_parent);
        //bt_temp=(Button)findViewById(R.id.bt_temp);
        //Buttons TExt

        data.add("HTML");
        data.add("WordPresssssssssss");
        data.add("PHP");
        data.add("Android");
        data.add("ReactNative");
        data.add("BuddyPress");
        data.add("ios");
        data.add("Nokia");
        data.add("PHP");

        //Screen Size

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        Toast.makeText(DynamicActivity.this, "Width==" + width, Toast.LENGTH_LONG).show();

    }
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
       bt_temp=(Button)findViewById(R.id.bt_temp);

        x=bt_temp.getWidth();
       Toast.makeText(DynamicActivity.this, "bt_temp_width=" + x, Toast.LENGTH_LONG).show();
        // Buttons

        for (int r = 0; r < 3; r++) {
            LinearLayout row = new LinearLayout(this);
            LinearLayout.LayoutParams row_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            row.setOrientation(LinearLayout.HORIZONTAL);

            for (int c = 0; c < 3; c++) {
                bt_add = new Button(this);
                bt_add.setText(data.get(count));
                count++;
                bt_add.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                bt_add.setId(c + 1 + (r * 4));
                bt_add.setTextSize(10);


                row.addView(bt_add);


            }
            layout.addView(row);
            Toast.makeText(DynamicActivity.this, "bt_width=" + bt_add.getWidth(), Toast.LENGTH_LONG).show();
        }


    }

}
