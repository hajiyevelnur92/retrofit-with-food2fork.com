package codehive.foodrecept.ui.activities;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import codehive.foodrecept.R;

public class SplashActivity extends AppCompatActivity {

    final Handler handler = new Handler();
    Timer timer;
    TimerTask timerTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        finish();
    }
}
