package com.tik.flutter_logrocket;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.logrocket.core.SDK;

import java.util.HashMap;
import java.util.Map;

public class IdentifyUser {
    String userId;
    String name;
    String email;
    void  identifyUser(){
        Map<String, String> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("email", email);
        userData.put("subscriptionPlan", "premium");
        Log.d(TAG, "User id: " + userId);
        Log.d(TAG, "User name: " + name);
        Log.d(TAG, "User email: " + email);
        SDK.identify(userId,userData);
    }
}
