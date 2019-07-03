package com.androidlabs.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int time;
    SeekBar seekBar;
    TextView textView;
    Button button1,button2;
    CountDownTimer counttimer;
    public void update(int seconds)
    {
        time=seconds;
        int min=(int)seconds/60;
        int sec=seconds-min*60;
        if(sec>9)
            textView.setText(Integer.toString(min)+":"+Integer.toString(sec) );
        else
            textView.setText(Integer.toString(min)+":0"+Integer.toString(sec) );
        seekBar.setProgress(time);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         seekBar=(SeekBar)findViewById(R.id.seekBar);
         textView=(TextView)findViewById(R.id.textView);
        button1=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
            seekBar.setMax(600);
            seekBar.setProgress(0);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    update(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
    }
    public void timer(final View view)
    {
        button1.setVisibility(view.INVISIBLE);
        button2.setVisibility(view.VISIBLE);
        counttimer=new CountDownTimer(time * 1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                update((int)millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("0:00");
                MediaPlayer mplayer=MediaPlayer.create(getApplicationContext(),R.raw.sound);
                mplayer.start();
                button1.setVisibility(view.VISIBLE);
                button2.setVisibility(view.INVISIBLE);
            }
        };
        counttimer.start();
    }
    public void stop(View view)
    {

        button1.setVisibility(view.VISIBLE);
        button2.setVisibility(view.INVISIBLE);
        counttimer.cancel();
    }
}
