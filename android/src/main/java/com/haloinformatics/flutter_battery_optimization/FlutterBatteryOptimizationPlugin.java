package com.example.flutter_battery_optimization;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.os.PowerManager;
import android.content.Intent;
import static android.content.Context.POWER_SERVICE;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.net.Uri;

/** FlutterBatteryOptimizationPlugin */
public class FlutterBatteryOptimizationPlugin implements MethodCallHandler {


  private PowerManager mPowerManager;
  private Registrar mRegistrar;
  public static void registerWith(Registrar registrar) {

    if (registrar.activity() == null) {
      return;
    }
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_battery_optimization");
   channel.setMethodCallHandler(new FlutterBatteryOptimizationPlugin(registrar));
  }

  FlutterBatteryOptimizationPlugin(Registrar registrar) {
    mRegistrar = registrar;
  }
  @Override
  public void onMethodCall(MethodCall call, Result result) {
     if (call.method.equals("isIgnoringBatteryOptimizations")) {
      boolean reading = isIgnoringBatteryOptimizations();
      result.success(reading);
      return;
    }
    else if (call.method.equals("openBatteryOptimizationSettings")) {
      boolean reading = openBatteryOptimizationSettings();
      result.success(reading);
      return;
    }else if(call.method.equals("stopOptimizingBatteryUsage")){
      boolean reading = stopOptimizingBatteryUsage();
      result.success(reading);
      return;
    }
    else {
      result.notImplemented();
    }
  }

  boolean isIgnoringBatteryOptimizations() {
    Intent intent = new Intent();
    String packageName = mRegistrar.activeContext().getPackageName();
    mPowerManager = (PowerManager) (mRegistrar.activeContext().getSystemService(POWER_SERVICE));
    if(mPowerManager.isIgnoringBatteryOptimizations(packageName)) {
      return true;
    } else {
      return false;
    }
  }

  boolean openBatteryOptimizationSettings() {
    Intent intent = new Intent();
    String packageName = mRegistrar.activeContext().getPackageName();
    intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
    mRegistrar.activeContext().startActivity(intent);
    return true;
  }


    boolean stopOptimizingBatteryUsage() {
    Intent intent = new Intent();
    String packageName = mRegistrar.activeContext().getPackageName();
    intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
    intent.setData(Uri.parse("package:" + packageName));
    mRegistrar.activeContext().startActivity(intent);
    return true;
  }

}
