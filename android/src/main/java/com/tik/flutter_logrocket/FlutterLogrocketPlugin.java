package com.tik.flutter_logrocket;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterLogrocketPlugin */
public class FlutterLogrocketPlugin extends Logrocket implements FlutterPlugin, MethodCallHandler   {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;
  private Logrocket logrocket;
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "flutter_logrocket");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    logrocket = new Logrocket();
    if(call.method.equals("initLogrocket")){
      logrocket.appId = call.argument("appId");
      Log.d(TAG, "Init Log Rocket with: " + logrocket.appId);
      logrocket.attachBaseContext(getBaseContext());
      
      
    }else if(call.method.equals("sendGetRequest")){
      logrocket.URL = call.argument("URL");
      try{
        logrocket.sendGetRequest();
      }catch (IOException e){
        Log.d(TAG, e.toString());
      }
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
