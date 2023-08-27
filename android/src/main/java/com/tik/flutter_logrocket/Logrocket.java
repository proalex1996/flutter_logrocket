package com.tik.flutter_logrocket;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.logrocket.core.SDK;

import java.util.HashMap;
import java.util.Map;

public class Logrocket extends Application {
    protected String appId;
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "Init Log Rocket with: " + appId);
        boolean init = SDK.init(
                this,
                base,
                options -> {
                    options.setAppID(appId);

                }
        );

        Log.d(TAG, "Init Log Rocket: " + init);
    }
}
