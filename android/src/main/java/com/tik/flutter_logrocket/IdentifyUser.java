package com.tik.flutter_logrocket;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.logrocket.core.SDK;

import java.util.HashMap;
import java.util.Map;

public class IdentifyUser {
    String userId;
    void  identifyUser(){
        Map<String, String> userData = new HashMap<>();
        userData.put("name", "Tinh");
        userData.put("email", "Tinh@gmail.com");
        userData.put("subscriptionPlan", "premium");
        Log.d(TAG, "User id: " + userId);
        SDK.identify(userId,userData);
    }
}
