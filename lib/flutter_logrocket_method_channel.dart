import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'flutter_logrocket_platform_interface.dart';

/// An implementation of [FlutterLogrocketPlatform] that uses method channels.
class MethodChannelFlutterLogrocket extends FlutterLogrocketPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('flutter_logrocket');

  @override
  void initLogrocket(String appId) {
    methodChannel.invokeMethod<String>(
        'initLogrocket', <String, dynamic>{"appId": appId});
  }

  @override
  void identifyUser(String userId) {
    methodChannel.invokeMethod<String>(
        'identifyUser', <String, dynamic>{"userId": userId});
  }
}
