import 'dart:async';

import 'package:flutter/services.dart';

class FlutterBatteryOptimization {
  static const MethodChannel _channel =
      const MethodChannel('flutter_battery_optimization');

static Future<bool> isIgnoringBatteryOptimizations() async {
    final reading = await _channel.invokeMethod('isIgnoringBatteryOptimizations');
    return reading;
  }

  static Future<bool> openBatteryOptimizationSettings() async {
    final reading = await _channel.invokeMethod('openBatteryOptimizationSettings');
    return reading;
  }

  static Future<bool> stopOptimizingBatteryUsage()async{
    final reading =await _channel.invokeMethod('stopOptimizingBatteryUsage');
    return reading;
  }


}
