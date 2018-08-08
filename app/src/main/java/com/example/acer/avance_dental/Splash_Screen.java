package com.example.acer.avance_dental;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.acer.avance_dental.AppUtils.AppConfig;

public class Splash_Screen extends AppCompatActivity {
    VideoView videoView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);

        videoView=findViewById(R.id.videoview);
        sharedPreferences = getSharedPreferences(AppConfig.SharedPref , Context.MODE_PRIVATE);

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splassss);
        videoView.setVideoURI(video);

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

    }

    private void startNextActivity() {


     String uid=   sharedPreferences.getString(AppConfig.Keyuid,"");
         String email=sharedPreferences.getString(AppConfig.Keyemail,"");
String token=        sharedPreferences.getString(AppConfig.Keytoken,"");
 if(uid.equals("")&&email.equals("")&&token.equals("")) {
     startActivity(new Intent(this, ViewPager.class));
 }else{
     startActivity(new Intent(this,HomePAge.class));

 }
    }
}
