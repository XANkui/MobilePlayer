package com.example.xan.mobileplayer.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.example.xan.mobileplayer.R;

public class SplashActivity extends Activity {

    private final static String TAG = SplashActivity.class.getName();
    private boolean isStartMain = false;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startMainActivity();
                Log.e(TAG,"当前线程名称:"+Thread.currentThread().getName());
            }
        },2000);
    }

    private void startMainActivity() {

        if(isStartMain ==false){
            isStartMain = true;

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"onTouchEvent==Action:"+event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
