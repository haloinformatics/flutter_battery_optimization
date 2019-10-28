import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_battery_optimization/flutter_battery_optimization.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_battery_optimization');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  }
