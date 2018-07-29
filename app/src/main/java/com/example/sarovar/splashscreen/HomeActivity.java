package com.example.sarovar.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class HomeActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mLoadingText;
    private int SPLASH_DISPLAY_LENGTH=1000;

    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mLoadingText = (TextView) findViewById(R.id.LoadingCompleteTextView);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (mProgressStatus < 100){
                            mProgressStatus++;
                            android.os.SystemClock.sleep(50);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(mProgressStatus);
                                }
                            });
                        }
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                               // Intent mainIntent = new Intent(HomeActivity.this,MenuActivity.class);
                                //HomeActivity.this.startActivity(mainIntent);
                                //HomeActivity.this.finish();
                                mLoadingText.setVisibility(View.VISIBLE);

                                startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                            }
                        });
                    }
                }).start();


            }
        }, SPLASH_DISPLAY_LENGTH);


    }
}
