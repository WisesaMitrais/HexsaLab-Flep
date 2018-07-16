package com.lab.hexsa.flep.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lab.hexsa.flep.R;
import com.lab.hexsa.flep.fragment.IntroSliderFragment;
import com.lab.hexsa.flep.fragment.IntroSliderFragment2;
import com.lab.hexsa.flep.fragment.IntroSliderFragment3;

import java.util.Timer;
import java.util.TimerTask;

public class SplashscreenActivity extends AppCompatActivity {
    private RelativeLayout introSlider1;
    private LinearLayout introSlider2, introSlider3, introSlider4, introSlider5,
            splashscreen1, iconImage, iconMusic, iconVideo;
    private Button buttonEnter;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private Timer swipeTimer;
    private int currentPage = 1;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            introSlider1.setVisibility(View.VISIBLE);
            introSlider2.setVisibility(View.VISIBLE);
            introSlider3.setVisibility(View.VISIBLE);
            introSlider4.setVisibility(View.VISIBLE);
            introSlider5.setVisibility(View.VISIBLE);
            splashscreen1.setVisibility(View.GONE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        introSlider1 = findViewById(R.id.intro_slider_1);
        introSlider2 = findViewById(R.id.intro_slider_2);
        introSlider3 = findViewById(R.id.intro_slider_3);
        introSlider4 = findViewById(R.id.intro_slider_4);
        introSlider5 = findViewById(R.id.intro_slider_5);
        splashscreen1 = findViewById(R.id.splashscreen_1);
        iconImage = findViewById(R.id.icon_image);
        iconMusic = findViewById(R.id.icon_music);
        iconVideo = findViewById(R.id.icon_video);
        buttonEnter = findViewById(R.id.intro_slider_button);
        handler.postDelayed(runnable, 2000);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/Quicksand-Medium.ttf");
        buttonEnter.setTypeface(font);

        viewPager = findViewById(R.id.intro_slider);
        pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(currentPage == 1){
                            iconImage.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_gradient_circle_background));
                            iconMusic.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                            iconVideo.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                        }else if(currentPage == 2){
                            iconImage.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                            iconMusic.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_gradient_circle_background));
                            iconVideo.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                        }else if(currentPage == 3){
                            iconImage.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                            iconMusic.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_circle_background));
                            iconVideo.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                    R.drawable.custom_gradient_circle_background));
                            currentPage = 0;
                        }
                        viewPager.setCurrentItem(currentPage++, true);
                    }
                });
            }
        }, 0, 4000);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashscreenActivity.this, DashboardActivity.class));
            }
        });
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            switch(position){
                case 1:
                    fragment = new IntroSliderFragment();
                    break;
                case 2:
                    fragment = new IntroSliderFragment2();
                    break;
                case 3:
                    fragment = new IntroSliderFragment3();
                    break;
                    default: fragment = new IntroSliderFragment3();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
