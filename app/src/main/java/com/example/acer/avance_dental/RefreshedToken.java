package com.example.acer.avance_dental;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.acer.avance_dental.AppUtils.AppConfig;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by hp on 8/3/2018.
 */

public class RefreshedToken extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        //now we will have the token
        String token = FirebaseInstanceId.getInstance().getToken();

        //for now we are displaying the token in the log
        //copy it as this method is called only when the new token is generated
        //and usually new token is only generated when the app is reinstalled or the data is cleared
        Log.d("MyRefreshedToken", token);
        SharedPreferences sharedPreferences=getSharedPreferences(AppConfig.TokenPref, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(AppConfig.TokenKey,token).commit();
    }
}
