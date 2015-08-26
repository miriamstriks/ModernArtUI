package com.example.miriam.modernartui;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends FragmentActivity {

    private SeekBar slider;
    private ArrayList<TextView> blocks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blocks = new ArrayList<TextView>();
        slider = (SeekBar) findViewById(R.id.slider);
        TextView block1 = (TextView) findViewById(R.id.colorblock1);
        TextView block2 = (TextView) findViewById(R.id.colorblock2);

        blocks.add(block1);
        blocks.add(block2);

        slider.setOnSeekBarChangeListener(seekBarChangeListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, Menu.FIRST, Menu.NONE, R.string.menu_option);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == Menu.FIRST) {
            displayMoreInfo();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener
            = new SeekBar.OnSeekBarChangeListener()
    {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
            updateColors();
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }
    };

    private void updateColors()
    {
        int position = slider.getProgress();
        ColorDrawable cd;
        int colorCode, a, r, g, b;
        float[] hsv = new float[3];

        for(TextView block : blocks) {
            cd = (ColorDrawable) block.getBackground();
            colorCode = cd.getColor();
            Color.colorToHSV(colorCode,hsv);
            hsv[0]=hsv[0]+3;

            a = cd.getAlpha();

            block.setBackgroundColor(Color.HSVToColor(a,hsv));
        }
    }
    private void displayMoreInfo(){

        FragmentManager fm = getSupportFragmentManager();
        MainActivityFragment infoFragment = new MainActivityFragment();
        infoFragment.show(fm,"fragment_main");

    }
}
