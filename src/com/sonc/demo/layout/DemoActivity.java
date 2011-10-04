package com.sonc.demo.layout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.sonc.demo.R;

public class DemoActivity extends Activity {
    private LinearLayout top;
    private LinearLayout bottom;
    private SeekBar      size;
    
    
    private int          hTop;
    private int          _hTop;
    private int          wBottom;
    private int          _wBottom;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        size   = (SeekBar)      findViewById(R.id.size);
        top    = (LinearLayout) findViewById(R.id.top);
        bottom = (LinearLayout) findViewById(R.id.bottom);
        
        bottom.post(new Runnable() {
            @Override
            public void run() {
                // determine the view's width after the view has been measured
                wBottom = bottom.getMeasuredWidth();
            }
        });

        top.post(new Runnable() {
            @Override
            public void run() {
                // determine the view's height after the view has been measured
                hTop = top.getMeasuredHeight();
            }
        });
        
        size.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // calculate the new desired width/height of the view
                _hTop    = (int) ((progress/100f)*hTop);
                _wBottom = (int) ((progress/100f)*wBottom);
                
                // set the padding accordingly
                top.setPadding(0, 0, 0, hTop - _hTop);
                bottom.setPadding(wBottom - _wBottom, 0, 0, 0);
            }
        });
    }
}