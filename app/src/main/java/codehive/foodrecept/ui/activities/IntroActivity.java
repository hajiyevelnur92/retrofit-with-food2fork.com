package codehive.foodrecept.ui.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

import codehive.foodrecept.MyApplication;
import codehive.foodrecept.R;


public class IntroActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Welcome", "THIS is simple tutoriol to show how to use retrofit with recycleview and cardview", R.drawable.retrofit, Color.parseColor("#ff4e00")));
        addSlide(AppIntroFragment.newInstance("Second", "Thanks for website food2fork.com which offers api call service", R.drawable.foortofork, Color.parseColor("#ff4e00")));
        setBarColor(Color.parseColor("#ff4e00"));
        showSkipButton(false);
        setProgressButtonEnabled(true);
        showStatusBar(false);
        //setFlowAnimation();
        setBackButtonVisibilityWithDone(true);
        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
        // Do something when users tap on Skip button.
        MyApplication.setIntroShown(this, true);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
        // Do something when users tap on Done button.
        MyApplication.setIntroShown(this, true);

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}