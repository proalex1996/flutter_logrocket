package com.tik.flutter_logrocket;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import java.io.IOException;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterLogrocketPlugin */
public class FlutterLogrocketPlugin extends Logrocket implements FlutterPlugin, MethodCallHandler  {
  private MethodChannel channel;
  private Logrocket logrocket;
  private IdentifyUser identifyUser;
  private Application application;
  Context context;


  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {

    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_logrocket");
    channel.setMethodCallHandler(this);
    this.context = flutterPluginBinding.getApplicationContext();
  }


  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {


    if(call.method.equals("initLogrocket")){
      appId = call.argument("appId");
      attachBaseContext(context);
    }else if(call.method.equals("identifyUser")){
      identifyUser = new IdentifyUser();
      identifyUser.userId = call.argument("userId");
      identifyUser.identifyUser();
    }  else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
