package com.sonc.demo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class DemoActivity extends Activity {
    private LinearLayout top;
    private LinearLayout bottom;
    private SeekBar size;
    
    private int hTop;
    private int _hTop;
    private int wBottom;
    private int _wBottom;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        size = (SeekBar) findViewById(R.id.size);
        top = (LinearLayout) findViewById(R.id.top);
        bottom = (LinearLayout) findViewById(R.id.bottom);
        
        bottom.post(new Runnable() {
            @Override
            public void run() {
                wBottom = bottom.getMeasuredWidth();
            }
        });

        top.post(new Runnable() {
            @Override
            public void run() {
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
                _hTop = (int) ((progress/100f)*hTop);
                _wBottom = (int) ((progress/100f)*wBottom);
                
                top.setPadding(0, 0, 0, hTop - _hTop);
                bottom.setPadding(wBottom - _wBottom, 0, 0, 0);
            }
        });
    }
}